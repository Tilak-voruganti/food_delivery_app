
package com.foodapp.DAO;

import java.util.List;
import com.foodapp.models.User;

public interface UserDAO {
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(int userid);
	User getUserById(int userid);
	User getUserByEmailId(String emailid);
	List<User> getAllUsers();
}
