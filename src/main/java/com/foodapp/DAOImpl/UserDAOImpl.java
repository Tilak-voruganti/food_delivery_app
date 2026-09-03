package com.foodapp.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.util.*;
import com.foodapp.DAO.UserDAO;
import com.foodapp.models.User;
import com.foodapp.util.PasswordUtil;

public class UserDAOImpl implements UserDAO{

	private final String INSERT = "INSERT into `user` (`name`, `username`, `password`, `email`, `phonenumber`, `address`, `role`, `createddate`, `lastlogindate`) values (?,?,?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE `user` SET `name` = ?, `username` = ?, `password` = ?, `email` = ?, `phonenumber` = ?, `address` = ?, `role` = ? WHERE `userid` = ?";
	private final String GET_USER_BY_ID = "SELECT * from `user` WHERE `userid` = ?";
	private final String GET_USER_BY_EMAIL_ID = "SELECT * from `user` WHERE LOWER(TRIM(`email`)) = LOWER(TRIM(?)) ORDER BY `userid` DESC LIMIT 1";
	private final String GET_ALL_USERS = "SELECT * from `user`";
	private final String DELETE = "DELETE from `user` WHERE `userid` = ?";

	@Override
	public void addUser(User user) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(INSERT);)
		{
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUsername());
			prepareStatement.setString(3, PasswordUtil.hash(user.getPassword()));
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhonenumber());
			prepareStatement.setString(6, user.getAddress());
			prepareStatement.setString(7, user.getRole());
			prepareStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			prepareStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

			int i = prepareStatement.executeUpdate();

			System.out.println(i);
			
			System.out.println("A new user has been added to the database...");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User user) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(UPDATE);)
		{
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUsername());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhonenumber());
			prepareStatement.setString(6, user.getAddress());
			prepareStatement.setString(7, user.getRole());
			prepareStatement.setInt(8, user.getUserid());
			int i = prepareStatement.executeUpdate();

			System.out.println(i);
		}
		catch(SQLException e){
			e.printStackTrace();
		}		
	}

	@Override
	public void deleteUser(int userid) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(DELETE);)
		{
			prepareStatement.setInt(1, userid);

			int i = prepareStatement.executeUpdate();

			System.out.println(i);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_ALL_USERS);)
		{
			ResultSet res = prepareStatement.executeQuery();

			while(res.next()) {
				int id = res.getInt("userid");
				String name = res.getString("name");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String phonenumber = res.getString("phonenumber");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createddate = res.getTimestamp("createddate");
				Timestamp lastlogindate = res.getTimestamp("lastlogindate");

				User u = new User(id, name, username, password, email, phonenumber, address, role, createddate, lastlogindate);
				users.add(u);
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserById(int userid) {

		User user = null;

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_ID);)
		{
			prepareStatement.setInt(1, userid);

			ResultSet res = prepareStatement.executeQuery();

			while(res.next()) {
				int id = res.getInt("userid");
				String name = res.getString("name");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String phonenumber = res.getString("phonenumber");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createddate = res.getTimestamp("createddate");
				Timestamp lastlogindate = res.getTimestamp("lastlogindate");

				user = new User(id, name, username, password, email, phonenumber, address, role, createddate, lastlogindate);
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public User getUserByEmailId(String emailid) {
		
		User user = null;

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement(GET_USER_BY_EMAIL_ID);)
		{
			prepareStatement.setString(1, emailid);

			ResultSet res = prepareStatement.executeQuery();

			while(res.next()) {
				int id = res.getInt("userid");
				String name = res.getString("name");
				String username = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String phonenumber = res.getString("phonenumber");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createddate = res.getTimestamp("createddate");
				Timestamp lastlogindate = res.getTimestamp("lastlogindate");

				user = new User(id, name, username, password, email, phonenumber, address, role, createddate, lastlogindate);
			}

		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
}