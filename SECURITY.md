# Security configuration

Implemented in the application:

- Prepared statements are used for database queries.
- PBKDF2-HMAC-SHA256 password hashing is used for new registrations. Existing plaintext passwords migrate after a successful login.
- A global servlet filter adds CSP, clickjacking, MIME-sniffing, referrer, permissions, and HSTS headers on HTTPS.
- Same-origin CORS is enforced; cross-origin requests are rejected.
- Requests are rate-limited per client address to 120 requests per minute.
- CSRF tokens protect POST forms.
- Cart and checkout require an authenticated session.
- Public registration always creates a Customer account; admin roles cannot be selected in the browser.
- Future `/admin/*` routes require the `Super Admin` role and the server-only `FOOD_ADMIN_EMAIL` allowlist.

Deployment requirements:

- Set `FOOD_DB_URL`, `FOOD_DB_USER`, and `FOOD_DB_PASSWORD` as environment variables. Do not commit database credentials.
- Terminate TLS at Tomcat or a reverse proxy and redirect HTTP to HTTPS before production use.
- Basic Auth, Bearer tokens, JWT, OAuth2, and SSO are different authentication protocols. They require an identity provider, client registration, key rotation, token validation, and callback URLs. They should be added through a chosen provider such as Keycloak, Microsoft Entra ID, Auth0, or Okta rather than implementing fake local versions.
- Configure the provider's issuer, audience, client ID, client secret, redirect URI, and JWKS endpoint in environment variables before enabling OAuth2/OIDC.

Local deployment example (PowerShell):

```powershell
$env:FOOD_DB_URL = 'jdbc:mysql://db-host:3306/food?useSSL=true&requireSSL=true&serverTimezone=UTC'
$env:FOOD_DB_USER = 'foodapp'
$env:FOOD_DB_PASSWORD = '<set-in-secret-manager>'
$env:FOOD_ADMIN_EMAIL = '<admin-email>'
```

Never place the real values in source files, JSP files, command history, or a public repository. Passwords already stored in legacy accounts are converted to PBKDF2 after their next successful login.
