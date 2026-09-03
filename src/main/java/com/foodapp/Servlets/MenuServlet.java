package com.foodapp.Servlets;

import java.io.IOException;
import java.util.List;

import com.foodapp.DAO.MenuDAO;
import com.foodapp.DAOImpl.MenuDAOImpl;
import com.foodapp.models.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Hi from menu servelt...");
		
		String restaurantIdParam = req.getParameter("restaurantId");
		int restaurantId;
		try {
			restaurantId = Integer.parseInt(restaurantIdParam);
		} catch (NumberFormatException e) {
			resp.sendRedirect("home");
			return;
		}
		
		MenuDAO mdao = new MenuDAOImpl();
		
		List<Menu> menuByRestaurantId = mdao.getMenuByRestaurantId(restaurantId);
		
		req.setAttribute("menuByRestaurantId", menuByRestaurantId);
		
		for(Menu menu : menuByRestaurantId) {
			 System.out.println(menu);
		}
			
		RequestDispatcher rd = req.getRequestDispatcher("/menu.jsp");
			
		rd.forward(req, resp);
	}
}