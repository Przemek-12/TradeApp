package org.openjfx.mavenfx.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openjfx.mavenfx.session.HibernateSession;
import org.openjfx.mavenfx.user.User;

public class databaseMethods {

	
	public void createUser(User user) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteUser(User user) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		session.delete(user);
		session.getTransaction().commit();
		session.close();
	}
	
	public User getUser(String userName, String password) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User where UserName=:username and Password=:password");
		query.setParameter("username", userName);
		query.setParameter("password", password);
		User user = (User)query.uniqueResult();
		session.getTransaction().commit();	
		session.close();
		System.out.println(session.isConnected());
		return user;
	}
	
	//get user by id
	public User getUser(int idUsers) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		User user = (User) session.get(User.class,idUsers);
		session.getTransaction().commit();
		session.close();
		return user;
	}
	
	//get user by username
	public User getUsername(String userName) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User where UserName=:username");
		query.setParameter("username", userName);
		User user = (User)query.uniqueResult();
		session.getTransaction().commit();	
		session.close();
		System.out.println(user);
		return user;
	}
	
	//get user by email
	public User getEmail(String email) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User where Email=:email");
		query.setParameter("email", email);
		User user = (User)query.uniqueResult();
		session.getTransaction().commit();	
		session.close();
		System.out.println(user);
		return user;
	}
	
	
	public List<User> userList(){
		
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		List<User> userList = session.createQuery("from User").list();
		session.getTransaction().commit();
		session.close();
		
		return userList;
	}
	
}
