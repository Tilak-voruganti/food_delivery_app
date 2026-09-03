<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Order Confirmation Page</title>
  <link type="image/png" rel="icon" href="images/food app header logo.png">
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: Arial, sans-serif;
    }

    body {
      background-color: white;
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
      position: relative;
      padding-bottom: 200px;
    }

    .confirmation-container {
      background-color: white;
      padding: 40px;
      border-radius: 10px;
      box-shadow: 2px 2px 10px #ff6f61;
      text-align: center;
      max-width: 650px;
    }

    .confirmation-container h1 {
      font-size: 26px;
      color: #333;
      margin-bottom: 20px;
    }

    .confirmation-container p {
      font-size: 18px;
      color: #555;
      margin-bottom: 10px;
    }

    /* Moving Image */
    .moving-image-container {
      position: fixed;
      bottom: 150px;
      width: 100%;
      height: 120px;
      pointer-events: none;
    }

    .moving-image {
      position: absolute;
      left: -300px;
      width: 250px;
      height: auto;
      animation: slideRight 10s linear infinite;
    }

    @keyframes slideRight {
      0% {
        left: -300px;
      }
      100% {
        left: 100%;
      }
    }

    /* Road */
    .road {
      position: fixed;
      bottom: 0px;
      width: 100%;
      height: 30px;
      background: #333;
      box-shadow: inset 0 5px 0 #555;
      z-index: 0;
    }

    .road::after {
      content: '';
      position: absolute;
      top: 50%;
      left: 0;
      width: 100%;
      height: 4px;
      background-image: repeating-linear-gradient(
        to right,
        white 0,
        white 40px,
        transparent 40px,
        transparent 80px
      );
      transform: translateY(-50%);
    }
  </style>
</head>
<body>

  <div class="confirmation-container">
    <h1>Thank You!</h1>
    <p>Your order has been placed successfully using the <strong>FoodZone</strong> app.</p>
    <p>We’re on our way to deliver happiness to your doorstep!</p>
  </div>

  <!-- Delivery guy moving image -->
  <div class="moving-image-container">
    <img src="image/Delivery Scooter.png" alt="Scooter Delivery" class="moving-image">
  </div>

  <!-- Road -->
  <div class="road"></div>

</body>
</html>