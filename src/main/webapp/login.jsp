<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login Page</title>
  <link type="image/png" rel="icon" href="images/food app header logo.png">
  <style>
  	* {
 		box-sizing: border-box;
	}
	
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
      background-image: url('images/login-bg.jpg');
      background-size: cover;
      background-position: center;
      background-repeat: no-repeat;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
	
	.login-container {
	  background: #fff;
	  padding: 30px;
	  border-radius: 16px;
	  box-shadow: 2px 2px 10px hsl(0, 0%, 0%);
	  width: 100%;
	  max-width: 400px;
	}

    .login-container h2 {
      margin-bottom: 30px;
      margin-top: center;
      font-size: 24px;
      color: #333;
      text-align: center;
    }

    .login-container label {
      display: block;
      text-align: left;
      margin-bottom: 6px;
      font-weight: bold;
      color: #333;
    }

    .login-container input {
      width: 100%;
      padding: 12px;
      margin-bottom: 20px;
      border: 1px solid #ddd;
      border-radius: 10px;
      font-size: 14px;
      transition: border-color 0.3s;
    }

    .login-container input:focus {
      border-color: #ff6f61;
      outline: none;
    }

    .login-btn {
      width: 100%;
      padding: 14px;
      background-color: #ff6f61;
      color: #fff;
      font-size: 16px;
      font-weight: bold;
      border: none;
      border-radius: 12px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .login-btn:hover {
      background-color: #e65b50;
    }

    .register-link {
      margin-top: 20px;
      font-size: 15px;
      color: #333;
    }

    .register-link a {
      color: #ff6f61;
      text-decoration: none;
      font-weight: bold;
      margin-left: 5px;
    }

    .register-link a:hover {
      text-decoration: underline;
    }

    .error-message {
      color: #ff6f61;
      margin-bottom: 15px;
      font-size: 14px;
      text-align: center;
    }
  </style>
</head>
<body>

  <div class="login-container">
    <h2>Welcome Back!<br>Ready to feast?</h2>

    <form action="login" method="post">
      <input type="hidden" name="csrfToken" value="<%= request.getAttribute("csrfToken") %>">
      <label for="email">Email ID</label>
      <input type="email" id="email" name="email" required>

      <label for="password">Password</label>
      <input type="password" id="password" name="password" autocomplete="current-password" required>

      <!-- Error message -->
      <% if(request.getAttribute("error") != null) { %>
        <div class="error-message">
          <%= com.foodapp.util.HtmlUtil.escape(String.valueOf(request.getAttribute("error"))) %>
        </div>
      <% } %>

      <button type="submit" class="login-btn">Login</button>
    </form>

    <p class="register-link">Don't have an account?<a href="userregistration.jsp">Register here</a></p>
  </div>

</body>
</html>