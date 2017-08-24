package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.User;
import com.forum.units.UserRole;
import com.forum.util.Utility;

public class UserService {
	public static ArrayList<User> users = new ArrayList<>();
	
	public static void createUser(String username, String password, String email, UserRole userRole) {
		if (Utility.isNotNullAndEmpty(username) && Utility.isNotNullAndEmpty(email) && Utility.isNotNullAndEmpty(password) && (userRole != null)) {
			if (getUser(username) == null) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setUserRole(userRole);
				user.setId();
				user.setId();
				user.setCreated();
				users.add(user);
			} else {
				System.out.println("Username or email already exists");
			}
		} else {
			System.out.println("Any specified field can't be empty");
		}
	}
	
	public static User getUser(String username, String password) {
		User user = getUser(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
	
	private static User getUser(String username) {
		for (User user : users) {
			if (user != null && (user.getUsername().equals(username) || user.getEmail().equals(username)))
				return user;
		}
		return null;
	}
}
