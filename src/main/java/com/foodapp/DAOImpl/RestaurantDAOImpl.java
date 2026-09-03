package com.foodapp.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.DAO.RestaurantDAO;
import com.foodapp.models.Restaurant;
import com.foodapp.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO{

	private final String INSERT = "INSERT into `restaurant` (`name`, `address`, `phonenumber`, `cusinetype`, `deliverytime`, `admineuserid`, `rating`, `isactive`, `imagepath`) values (?, ?, ?, ? , ? , ? , ? , ? , ?)";
	private final String UPDATE = "UPDATE `restaurant` SET `name` = ?, `address` = ?, `phonenumber` = ?, `cusinetype` = ?, `deliverytime` = ?, `admineuserid` = ?, `rating` = ?, `isactive` = ?, `imagepath` = ? WHERE `restaurantid` =?";
	private final String GET_RESTAURANT_BY_ID = "SELECT * from `restaurant` WHERE `restaurantid` = ?";
	private final String GET_ALL_RESTAURANTS = "SELECT * from `restaurant`";
	private final String DELETE = "DELETE FROM `restaurant` WHERE `restaurantid` = ?";
	private final String SEARCH_RESTAURANTS = "SELECT * FROM `restaurant` WHERE LOWER(`name`) LIKE ? OR LOWER(`address`) LIKE ? OR LOWER(`cusinetype`) LIKE ?";
	
	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT);)
		{
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhonenumber());
			prepareStatement.setString(4, restaurant.getCusinetype());
			prepareStatement.setString(5, restaurant.getDeliverytime());
			prepareStatement.setInt(6, restaurant.getAdmineuserid());
			prepareStatement.setString(7, restaurant.getRating());
			prepareStatement.setString(8, restaurant.getIsactive());
			prepareStatement.setString(9, restaurant.getImagepath());
			int i = prepareStatement.executeUpdate();
			
			System.out.println(i);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE);)
		{
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhonenumber());
			prepareStatement.setString(4, restaurant.getCusinetype());
			prepareStatement.setString(5, restaurant.getDeliverytime());
			prepareStatement.setInt(6, restaurant.getAdmineuserid());
			prepareStatement.setString(7, restaurant.getRating());
			prepareStatement.setString(8, restaurant.getIsactive());
			prepareStatement.setString(9, restaurant.getImagepath());
			prepareStatement.setInt(10, restaurant.getRestaurantid());
			
			int i = prepareStatement.executeUpdate();
			
			System.out.println(i);
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteRestaurant(int restaurantid) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE);)
		{
			prepareStatement.setInt(1, restaurantid);
			
			int i = prepareStatement.executeUpdate();
			
			System.out.println(i);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurantById(int restaurantid) {
		
		Restaurant restaurant = null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_RESTAURANT_BY_ID);)
		{
			prepareStatement.setInt(1, restaurantid);
			
			ResultSet res = prepareStatement.executeQuery();

			while(res.next()) {
				int id = res.getInt("restaurantid");
				String name = res.getString("name");
				String address = res.getString("address");
				String phonenumber = res.getString("phonenumber");
				String cusinetype = res.getString("cusinetype");
				String deliverytime = res.getString("deliverytime");
				int admineuserid = res.getInt("admineuserid");
				String rating = res.getString("rating");
				String isactive = res.getString("isactive");
				String imagepath = res.getString("imagepath");
				
				restaurant = new Restaurant(id, name, address, phonenumber, cusinetype, deliverytime, admineuserid, rating, isactive, imagepath);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return restaurant;
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_RESTAURANTS);)
		{
			ResultSet res = prepareStatement.executeQuery();

			while(res.next()) {
				int id = res.getInt("restaurantid");
				String name = res.getString("name");
				String address = res.getString("address");
				String phonenumber = res.getString("phonenumber");
				String cusinetype = res.getString("cusinetype");
				String deliverytime = res.getString("deliverytime");
				int admineuserid = res.getInt("admineuserid");
				String rating = res.getString("rating");
				String isactive = res.getString("isactive");
				String imagepath = res.getString("imagepath");
				
				Restaurant r = new Restaurant(id, name, address, phonenumber, cusinetype, deliverytime, admineuserid, rating, isactive, imagepath);
				restaurants.add(r);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return restaurants;
	}

	@Override
	public List<Restaurant> searchRestaurants(String query) {
		
		List<Restaurant> results = new ArrayList<>();
	    String searchTerm = "%" + query.toLowerCase() + "%";
	    
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement prepareStatement = connection.prepareStatement(SEARCH_RESTAURANTS)) {
	        
	        prepareStatement.setString(1, searchTerm);
	        prepareStatement.setString(2, searchTerm);
	        prepareStatement.setString(3, searchTerm);
	        
	        ResultSet res = prepareStatement.executeQuery();

	        while(res.next()) {
	            int id = res.getInt("restaurantid");
	            String name = res.getString("name");
	            String address = res.getString("address");
	            String phonenumber = res.getString("phonenumber");
	            String cusinetype = res.getString("cusinetype");
	            String deliverytime = res.getString("deliverytime");
	            int admineuserid = res.getInt("admineuserid");
	            String rating = res.getString("rating");
	            String isactive = res.getString("isactive");
	            String imagepath = res.getString("imagepath");
	            
	            Restaurant r = new Restaurant(id, name, address, phonenumber, cusinetype, deliverytime, admineuserid, rating, isactive, imagepath);
	            results.add(r);
	        }
	    } catch(SQLException e) {
	        e.printStackTrace();
	    }
		return results;
	}
}