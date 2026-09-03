package com.foodapp.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
    private static final int MAX_REQUESTS_PER_MINUTE = 120;
    private final Map<String, Window> windows = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String origin = httpRequest.getHeader("Origin");

        if (origin != null && !origin.equals(httpRequest.getScheme() + "://" + httpRequest.getServerName()
                + ((httpRequest.getServerPort() == 80 || httpRequest.getServerPort() == 443) ? "" : ":" + httpRequest.getServerPort()))) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Cross-origin request rejected");
            return;
        }
        if (!allow(httpRequest.getRemoteAddr())) {
            httpResponse.setHeader("Retry-After", "60");
            httpResponse.sendError(429, "Too many requests");
            return;
        }

        if (httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/admin")
                && !isAdmin(httpRequest)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Administrator access required");
            return;
        }

        httpResponse.setHeader("Content-Security-Policy", "default-src 'self'; img-src 'self' https: data:; "
                + "style-src 'self' 'unsafe-inline' https://cdnjs.cloudflare.com; "
                + "script-src 'self'; font-src 'self' https://cdnjs.cloudflare.com; form-action 'self'");
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");
        httpResponse.setHeader("X-Frame-Options", "DENY");
        httpResponse.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
        httpResponse.setHeader("Permissions-Policy", "camera=(), microphone=(), geolocation=()");
        if (httpRequest.isSecure()) {
            httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        }

        HttpSession session = httpRequest.getSession();
        if (session.getAttribute("csrfToken") == null) {
            session.setAttribute("csrfToken", java.util.UUID.randomUUID().toString());
        }
        httpRequest.setAttribute("csrfToken", session.getAttribute("csrfToken"));
        if ("POST".equalsIgnoreCase(httpRequest.getMethod()) && !isValidCsrf(httpRequest, session)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
            return;
        }
        chain.doFilter(request, response);
    }

    private boolean isValidCsrf(HttpServletRequest request, HttpSession session) {
        String expected = (String) session.getAttribute("csrfToken");
        String submitted = request.getParameter("csrfToken");
        return expected != null && expected.equals(submitted);
    }

    private boolean isAdmin(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if (!(user instanceof com.foodapp.models.User)) {
            return false;
        }
        com.foodapp.models.User account = (com.foodapp.models.User) user;
        String configuredAdminEmail = System.getenv("FOOD_ADMIN_EMAIL");
        return "Super Admin".equals(account.getRole())
                && configuredAdminEmail != null
                && configuredAdminEmail.equalsIgnoreCase(account.getEmail());
    }

    private boolean allow(String address) {
        long now = System.currentTimeMillis();
        Window window = windows.computeIfAbsent(address, ignored -> new Window(now));
        synchronized (window) {
            if (now - window.startedAt >= 60000) {
                window.startedAt = now;
                window.count.set(0);
            }
            return window.count.incrementAndGet() <= MAX_REQUESTS_PER_MINUTE;
        }
    }

    private static final class Window {
        private long startedAt;
        private final AtomicInteger count = new AtomicInteger();

        private Window(long startedAt) {
            this.startedAt = startedAt;
        }
    }
}
