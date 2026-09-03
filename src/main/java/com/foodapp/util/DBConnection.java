package com.foodapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL = System.getenv("FOOD_DB_URL");
	private static final String USERNAME = System.getenv("FOOD_DB_USER");
	private static final String PASSWORD = System.getenv("FOOD_DB_PASSWORD");
	private static Connection connection = null;
	
	public static final Connection getConnection(){
		
		try {
			if (URL == null || URL.isBlank() || USERNAME == null || USERNAME.isBlank()
					|| PASSWORD == null || PASSWORD.isBlank()) {
				throw new SQLException("FOOD_DB_URL, FOOD_DB_USER, and FOOD_DB_PASSWORD must be configured");
			}
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}