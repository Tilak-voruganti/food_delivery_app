package com.foodapp.DAO;

import java.beans.Statement;
import java.util.List;
import com.foodapp.models.Order;

public interface OrderDAO {
	int addOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(int orderid);
	Order getOrderById(int orderid);
	List<Order> getAllOrders();
}