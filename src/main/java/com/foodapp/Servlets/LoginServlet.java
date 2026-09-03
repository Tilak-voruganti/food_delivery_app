
package com.foodapp.Servlets;

import java.io.IOException;

import com.foodapp.DAO.UserDAO;
import com.foodapp.DAOImpl.UserDAOImpl;
import com.foodapp.models.User;
import com.foodapp.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	int count = 1;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Hi from login servlet");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email != null) {
            email = email.trim();
        }
        
        HttpSession session = req.getSession();
        Integer attempts = (Integer) session.getAttribute("loginAttempts");
        
        if (attempts == null) attempts = 0;

        UserDAO udao = new UserDAOImpl();
        User user = udao.getUserByEmailId(email);
        
        if (user == null) {
            req.setAttribute("error", "Email not registered. Click on 'register here' to create an account</a>");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else if (password == null || !PasswordUtil.matches(password, user.getPassword())) {
            if (attempts >= 3) {
                req.setAttribute("error", "Account locked after 3 failed attempts. Enter the correct password to unlock this session.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return;
            }

            attempts++;
            session.setAttribute("loginAttempts", attempts);
            String message;
            if (attempts >= 3) {
                message = "Account locked after 3 failed attempts.";
            } else {
                int remaining = 3 - attempts;
                message = String.format("Wrong password! %d attempt%s remaining.", remaining, remaining > 1 ? "s" : "");
            }
            req.setAttribute("error", message);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } 
        else {
            // Successful login
            if (PasswordUtil.isLegacy(user.getPassword())) {
                user.setPassword(PasswordUtil.hash(password));
                new UserDAOImpl().updateUser(user);
            }
            session.setAttribute("userId", user.getUserid());
            session.setAttribute("userAddress", user.getAddress());
            session.setAttribute("user", user);
            session.removeAttribute("loginAttempts");
            req.getRequestDispatcher("home").forward(req, resp);
        }
	}
}