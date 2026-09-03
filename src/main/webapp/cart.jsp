<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.models.Cart, com.foodapp.models.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Cart</title>
  <link type="image/png" rel="icon" href="images/food app header logo.png">
    <style type="text/css">
    .cart-container{
      max-width: 500px;
      margin: 0 auto;
      background: #fff;
      padding: 20px;
      border-radius: 16px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    .cart-row {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      border-bottom: 1px solid #ddd;
      padding: 16px 0;
    }

    .cart-left p {
      margin: 4px 0;
      font-size: 15px;
      color: #333;
    }

    .cart-left .item-name {
      font-weight: 600;
      font-size: 16px;
    }

    .cart-right {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 10px;
    }

    .qty-box {
      display: flex;
      align-items: center;
      gap: 8px;
    }

    .qty-btn {
      background-color: #ff6f61;
      color: #fff;
      border: none;
      padding: 6px 12px;
      font-size: 16px;
      border-radius: 50%;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .qty-btn:hover {
      background-color: #e65b50;
    }

    .qty-display {
      min-width: 20px;
      text-align: center;
      font-weight: bold;
    }

    .remove-btn {
      background-color: transparent;
      border: 1px solid #ff6f61;
      color: #ff6f61;
      padding: 4px 10px;
      font-size: 12px;
      border-radius: 12px;
      cursor: pointer;
      transition: 0.3s ease;
    }

    .remove-btn:hover {
      background-color: #ff6f61;
      color: white;
    }

    .grand-total {
      display: flex;
      justify-content: space-between;
      padding: 20px 0 10px;
      font-size: 16px;
      font-weight: bold;
      color: #333;
    }

    .cart-actions {
      display: flex;
      flex-direction: column;
      gap: 12px;
      margin-top: 10px;
    }

    .action-btn {
      background-color: #ff6f61;
      color: white;
      text-decoration: none;
      text-align: center;
      border: none;
      padding: 12px;
      font-size: 15px;
      border-radius: 10px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    .action-btn:hover {
      background-color: #e65b50;
    }

    .proceed {
      width: 100%;
      background-color: #333;
    }

    .proceed:hover {
      background-color: #000;
    }
  </style>
</head>
<body>
  <h1 style="text-align: center; margin: 20px 0; color: #333;">Your Cart</h1>

  <div class="cart-container">
    <%
		  Cart cart = (Cart)session.getAttribute("cart");
		  Integer restaurantId = (Integer)session.getAttribute("restaurantId");
		
		  if(cart!=null && !cart.getItems().isEmpty())
		  {
			  for(CartItem item : cart.getItems().values())
			  { %>
			    <!-- Cart Item -->
			    <div class="cart-row">
			      <div class="cart-left">
      			        <p class="item-name"><%= com.foodapp.util.HtmlUtil.escape(item.getName()) %></p>
			        <p class="item-price">Price: ₹ <%= item.getPrice() %></p>
			        <p class="item-total">Total: ₹ <%= item.getTotalprice() %></p>
			      </div>
			
			      <div class="cart-right">
			        <div class="qty-box">
		                <!-- Minus (-) button form -->
		                <form action="cart" method="post">
                        <input type="hidden" name="csrfToken" value="<%= request.getAttribute("csrfToken") %>">
		                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
		                    <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
		                    <input type="hidden" name="action" value="update">
		                    <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
		                    <button class="qty-btn" <% if(item.getQuantity() == 1) { %>disabled<% } %>>−</button>
		                </form>
						<span class="qty-display"><%= item.getQuantity() %></span>
		                <!-- Plus (+) button form -->
		                <form action="cart" method="post">
                        <input type="hidden" name="csrfToken" value="<%= request.getAttribute("csrfToken") %>">
		                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
		                    <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
		                    <input type="hidden" name="action" value="update">
		                    <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
		                    <button class="qty-btn">+</button>
		                </form>
			        </div>
			        <form action="cart" method="post">
                  	<input type="hidden" name="csrfToken" value="<%= request.getAttribute("csrfToken") %>">
			        	<input type="hidden" name="itemId" value="<%= item.getItemId() %>">
			        	<input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") %>">
			        	<input type="hidden" name="action" value="remove">
			        	<button class="remove-btn">Remove</button>
			        </form>
			      </div>
			    </div>
				  
			<% }
		  }
		  else{ %>
			  <p>Your cart is empty, please add items to proceed to checkout.</p>
			  <div class="cart-actions">
      			<a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="action-btn">Add More Items</a>
      		  </div>
		<% } %>
		
		<%
			if(cart != null && !cart.getItems().isEmpty())
			{ %>
				<!-- Grand Total -->
			    <div class="grand-total">
			      <span>Grand Total</span>
			      <span>₹ <%= cart.getGrandTotal() %></span>
			    </div>
			
			    <!-- Action Buttons -->
			    <div class="cart-actions">
			      <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="action-btn">Add More Items</a>
			      <form action="checkout.jsp" method="Post">
                	<input type="hidden" name="csrfToken" value="<%= request.getAttribute("csrfToken") %>">
			      	<input type="submit" value="Proceed to Checkout" class="action-btn proceed">
			      </form>
			    </div>
		<% } %>
  </div>
</body>
</html>