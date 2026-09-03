# FoodZone Food Delivery App

A Jakarta Servlet and JSP food delivery application with restaurant browsing, menus, cart management, checkout, customer registration, and login.

## Features

- Restaurant and menu browsing
- Search restaurants
- Add dishes to a cart from multiple restaurants
- Increase or decrease item quantities
- Checkout and order confirmation
- Customer-only public registration
- PBKDF2 password hashing for new registrations
- CSRF protection, rate limiting, security headers, same-origin CORS, and XSS-safe output

## Requirements

- Java 17+
- Apache Tomcat 10.1+
- MySQL 8+
- MySQL Connector/J

## Database Configuration

The application does not contain database credentials. Configure these environment variables before starting Tomcat:

```powershell
$env:FOOD_DB_URL = 'jdbc:mysql://your-db-host:3306/food?useSSL=true&requireSSL=true&serverTimezone=UTC'
$env:FOOD_DB_USER = 'foodapp'
$env:FOOD_DB_PASSWORD = '<use-a-secret-manager>'
$env:FOOD_ADMIN_EMAIL = '<admin-email>'
```

Create the `food` database and the required tables before starting the application. Do not expose MySQL port 3306 to the public internet.

## Run Locally

1. Import the project into Eclipse as an existing Dynamic Web Project.
2. Configure Apache Tomcat 10.1 in Eclipse.
3. Add MySQL Connector/J to the Tomcat or project runtime.
4. Set the database environment variables.
5. Run the project on Tomcat.
6. Open `http://localhost:8080/FoodApp/home`.

## Deploy the WAR

The deployable WAR is excluded from GitHub by design. Build the project in Eclipse or package the compiled web application as `FoodApp.war`, then copy it into Tomcat's `webapps` directory. Configure the environment variables on the deployment server before starting Tomcat.

## Security

See [SECURITY.md](SECURITY.md) for the security controls and deployment requirements. OAuth2, SSO, JWT, Bearer tokens, Basic Auth, and production HTTPS require configuration with a real identity provider and TLS certificate.
