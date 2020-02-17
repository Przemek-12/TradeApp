package org.openjfx.mavenfx.user;

public class UserSingleton {
	
	private UserSingleton() {}
	
	
	private static User user;
	
	
	public static void setUser(User u) {
		user=u;
	}
	
	public static User getUser() {
		return user;
	}
	
}
