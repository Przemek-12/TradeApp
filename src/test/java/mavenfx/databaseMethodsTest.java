package mavenfx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openjfx.mavenfx.user.User;
import org.openjfx.mavenfx.user.databaseMethods;

public class databaseMethodsTest {

	private static databaseMethods db;
	private static User user;
	
	@BeforeClass
	public static void setup() {
		db = new databaseMethods();
		user = new User("testn", "testp", "teste");
	}
	
	@Test
	public void createGetUserTest() {
		db.createUser(user);
		User user2 = db.getUser("testn", "testp");
		assertTrue(user.equals(user2));
	}
	
	
	@Test
	public void getUsernameTest() {
		User user3 = db.getUsername("testn");
		assertEquals("testn", user3.getUserName());
	}
	
	@Test
	public void getEmailTest() {
		User user4 = db.getEmail("teste");
		assertEquals("teste", user4.getEmail());
	}
	
	@Test
	public void getUserListTest() {
		List<User> list = db.userList();
		assertFalse(list.isEmpty());
	}
	
	
	@AfterClass
	public static void deleteUser() {
		db.deleteUser(user);
	}
}
