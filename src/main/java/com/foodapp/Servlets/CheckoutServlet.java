package com.foodapp.Servlets;

import java.io.IOException;
import java.sql.Timestamp;

import com.foodapp.models.*;
import com.foodapp.DAO.*;
import com.foodapp.DAOImpl.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	
	private OrderDAO odao;
	
	@Override
	public void init() throws ServletException {
		odao = new OrderDAOImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		
//		if(user==null) {
//			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
//			rd.forward(req, resp);
//		}
		
		if(cart!=null && user!=null && !cart.getItems().isEmpty()) {
			String paymentmode = req.getParameter("payment");
			String address = (String)session.getAttribute("userAddress");
			
			Order order = new Order();
			order.setUserid(user.getUserid());
			order.setRestaurantid( (int)session.getAttribute("restaurantId") );
			order.setOrderdate(new Timestamp(System.currentTimeMillis()));
			order.setPaymentmode(paymentmode);
			order.setStatus("Confirmed");
			order.setTotalamount((int)cart.getGrandTotal());
			
			int orderid = odao.addOrder(order);
			
			for(CartItem item : cart.getItems().values()) {
				int itemId = item.getItemId();
				int quantity = item.getQuantity();
				int totalprice = (int)item.getTotalprice();
				
				OrderItem orderitem = new OrderItem(orderid, itemId, quantity, totalprice);
				
				OrderItemDAOImpl orderI;
				orderI = new OrderItemDAOImpl();
				orderI.addOrderItem(orderitem);
			}
			
			session.removeAttribute("cart");
			session.setAttribute("order", order);
			
			resp.sendRedirect("order_conformation.jsp");
		}
		else {
			resp.sendRedirect("cart.jsp");
		}
	}
}