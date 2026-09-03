<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Registration Page</title>
    <link type="image/png" rel="icon" href="images/food app header logo.png">
    <style>
      body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background-image: url('images/user-register-bg.jpg');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
      }

      .form-container {
        background: #fff;
        padding: 40px 30px;
        border-radius: 16px;
        box-shadow: 2px 2px 10px black;
        width: 100%;
        max-width: 500px;
        box-sizing: border-box;
      }

      .form-container h2 {
        text-align: center;
        margin-bottom: 30px;
        color: #333;
      }

      .form-container label {
        display: block;
        margin-bottom: 6px;
        font-weight: bold;
        color: #333;
      }

      .form-container input,
      .form-container textarea,
      .form-container select {
        width: 100%;
        padding: 12px;
        margin-bottom: 20px;
        border: 1px solid #ddd;
        border-radius: 10px;
        font-size: 14px;
        box-sizing: border-box;
        transition: border-color 0.3s ease;
      }

      .form-container input:focus,
      .form-container textarea:focus,
      .form-container select:focus {
        border-color: #ff6f61;
        outline: none;
      }

      .form-container button[type="submit"] {
        width: 100%;
        padding: 14px;
        background-color: #ff6f61;
        color: white;
        font-size: 16px;
        font-weight: bold;
        border: none;
        border-radius: 12px;
        cursor: pointer;
        transition: background-color 0.3s ease;
      }

      .form-container button[type="submit"]:hover {
        background-color: #e65b50;
      }
    </style>
</head>
<body>
  <div class="form-container">
    <h2>User Registration</h2>
    <form action="user-resgistration" method="post" id="user-form">
      <input type="hidden" name="csrfToken" value="<%= request.getAttribute("csrfToken") %>">
      <label for="name">Full Name</label>
      <input type="text" id="name" name="name" required>

      <label for="username">Username</label>
      <input type="text" id="username" name="username" required>

      <label for="password">Password</label>
      <input type="password" id="password" name="password" autocomplete="new-password" required>

      <label for="email">Email</label>
      <input type="email" id="email" name="email" required>

      <label for="phone">Phone Number</label>
      <input type="tel" id="phone" name="phonenumber" required>

      <label for="address">Address</label>
      <textarea id="address" name="address" rows="3" required></textarea>

      <button type="submit">Submit</button>
    </form>
  </div>
</body>
</html>