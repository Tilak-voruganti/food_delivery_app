package com.foodapp.DAO;

import java.util.List;
import com.foodapp.models.Menu;

public interface MenuDAO {
	void addMenu(Menu menu);
	void updateMenu(Menu menu);
	void deleteMenu(int menuid);
	Menu getMenuById(int menuid);
	List<Menu> getMenuByRestaurantId(int restaurantid);
	List<Menu> getAllMenu();
}