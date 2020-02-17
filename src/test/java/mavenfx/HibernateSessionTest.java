package mavenfx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.openjfx.mavenfx.session.HibernateSession;

public class HibernateSessionTest {

	
	@Test
	// checks if session factory taken multple times is the same object
	public void createSessionFactoryTest() {
		SessionFactory sf1 = HibernateSession.createSessionFactory();
		SessionFactory sf2 = HibernateSession.createSessionFactory();
		
		assertEquals(sf1.hashCode(),sf2.hashCode());
	}
	
	@Test
	public void connectionTest() {
		Session s = HibernateSession.createSessionFactory().openSession();
		assertTrue(s.isConnected());
	}
	
}
