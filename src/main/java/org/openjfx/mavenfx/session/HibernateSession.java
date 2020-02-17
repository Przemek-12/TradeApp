package org.openjfx.mavenfx.session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.openjfx.mavenfx.user.User;



public class HibernateSession {
	
	private static SessionFactory sessionFactory;
	
	private HibernateSession() {}
	
	public static SessionFactory createSessionFactory()  {
		if(sessionFactory==null) {
			try {
				Configuration config  = new Configuration()
						.addAnnotatedClass(org.openjfx.mavenfx.user.User.class)
						.addAnnotatedClass(org.openjfx.mavenfx.trades.Trade.class)
						.addAnnotatedClass(org.openjfx.mavenfx.trades.UserStock.class)
						.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
				        .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mavenfxdatabase?useTimezone=true&serverTimezone=UTC")                                
				        .setProperty("hibernate.connection.username", "root")  
				        .setProperty("hibernate.connection.password", "root")
				        .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
				        .setProperty("hibernate.show_sql", "true")
				        .setProperty("hibernate.connection.pool_size", "10");
				        
				        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
				        sessionFactory = config.buildSessionFactory(builder.build());
			
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		return sessionFactory;
	}
	
	public static void closeSession() throws Exception {
		createSessionFactory().close();
	}
		
}
