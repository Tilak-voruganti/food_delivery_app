package com.foodapp.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.DAO.OrderItemDAO;
import com.foodapp.models.OrderItem;
import com.foodapp.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO{

	private final String INSERT = "INSERT into `order_item` (`orderid`, `menuid`, `quantity`, `totalamount`) values (?, ?, ?, ?)";
	private final String UPDATE = "UPDATE `order_item` SET `orderid` = ?, `menuid` = ?, `quantity` = ?, `totalamount` = ? WHERE `orderitemid` = ?";
	private final String GET_ORDERITEM_BY_ID = "SELECT * from `order_item` WHERE `orderitemid` = ?";
	private final String GET_ALL_ORDERITEMS = "SELECT * from `order_item`";
	private final String DELETE = "DELETE from `order_item` WHERE `orderitemid` = ?";

	@Override
	public void addOrderItem(OrderItem orderitem) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT);)
		{

			prepareStatement.setInt(1, orderitem.getOrderid());
			prepareStatement.setInt(2, orderitem.getMenuid());
			prepareStatement.setInt(3, orderitem.getQuantity());
			prepareStatement.setInt(4, orderitem.getTotalamount());

			int i = prepareStatement.executeUpdate();

			System.out.println(i);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateOrderItem(OrderItem orderitem) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE);)
		{

			prepareStatement.setInt(1, orderitem.getOrderid());
			prepareStatement.setInt(2, orderitem.getMenuid());
			prepareStatement.setInt(3, orderitem.getQuantity());
			prepareStatement.setInt(4, orderitem.getTotalamount());
			prepareStatement.setInt(5, orderitem.getOrderitemid());

			int i = prepareStatement.executeUpdate();

			System.out.println(i);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int orderitemid) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE);)
		{

			prepareStatement.setInt(1, orderitemid);

			int i = prepareStatement.executeUpdate();

			System.out.println(i);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItemById(int orderitemid) {
		
		OrderItem orderitem = null;
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ORDERITEM_BY_ID);)
		{

			prepareStatement.setInt(1, orderitemid);

			ResultSet res = prepareStatement.executeQuery();
			
			while(res.next()) {
				int id = res.getInt("orderitemid");
				int orderid = res.getInt("orderid");
				int menuid = res.getInt("menuid");
				int quantity = res.getInt("quantity");
				int totalamount = res.getInt("quantity");
				
				orderitem = new OrderItem(id, orderid, menuid, quantity, totalamount);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return orderitem;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		
		List<OrderItem> orderitems = new ArrayList<OrderItem>();
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_ORDERITEMS);)
		{
			ResultSet res = prepareStatement.executeQuery();
			
			while(res.next()) {
				int id = res.getInt("orderitemid");
				int orderid = res.getInt("orderid");
				int menuid = res.getInt("menuid");
				int quantity = res.getInt("quantity");
				int totalamount = res.getInt("quantity");
				
				OrderItem ord_item = new OrderItem(id, orderid, menuid, quantity, totalamount);
				orderitems.add(ord_item);
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return orderitems;
	}

}