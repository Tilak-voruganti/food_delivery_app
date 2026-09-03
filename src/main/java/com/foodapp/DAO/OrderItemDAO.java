package com.foodapp.DAO;

import java.util.List;
import com.foodapp.models.OrderItem;

public interface OrderItemDAO {
	void addOrderItem(OrderItem orderitem);
	void updateOrderItem(OrderItem orderitem);
	void deleteOrderItem(int orderitemid);
	OrderItem getOrderItemById(int orderitemid);
	List<OrderItem> getAllOrderItems();
}