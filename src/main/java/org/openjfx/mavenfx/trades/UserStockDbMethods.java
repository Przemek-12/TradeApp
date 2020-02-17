package org.openjfx.mavenfx.trades;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openjfx.mavenfx.MenuScene;
import org.openjfx.mavenfx.session.HibernateSession;

public class UserStockDbMethods {
	
	//saves user id in row 
	public void saveStockUser(UserStock stock) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		session.save(stock);
		session.getTransaction().commit();
		session.close();
	}
	
	// list of all stocks from database to display in userScene
	public List<UserStock> getUserStockList(int idUser){
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UserStock> stockList = session.createQuery("from UserStock S where S.idUsers="+String.valueOf(idUser)).list();
		session.getTransaction().commit();
		session.close();
		return stockList;
	}
	
	
	// updates amount of stocks that user has - buy transaction
	public void setBuyStock(int idUsers, String company, int amount) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("select "+company+" from UserStock where idUsers=:idUsers ");
		query.setParameter("idUsers", idUsers);
		
		//present stock value
		int tempStock = (Integer)query.uniqueResult();
		int insertValue=tempStock+amount;

		Query query2 = session.createQuery("update UserStock set "+company+"=:insertValue where idUsers=:idUsers");
		query2.setParameter("idUsers", idUsers);
		query2.setParameter("insertValue", insertValue);
		query2.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	
	// updates amount of stocks that user has - sell transaction
	public boolean setSellStock(int idUsers, String company, int amount) {
		boolean hasEnoughStocks=true;
		
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("select "+company+" from UserStock where idUsers=:idUsers ");
		query.setParameter("idUsers", idUsers);
		
		//present stock value
		int tempStock = (Integer)query.uniqueResult();
		
		if(tempStock-amount<0) {
			MenuScene.messageLabel.setText("You don't have that many stocks");
			hasEnoughStocks=false;
		}
		else {
			int insertValue=tempStock-amount;
			Query query2 = session.createQuery("update UserStock set "+company+"=:insertValue where idUsers=:idUsers");
			query2.setParameter("idUsers", idUsers);
			query2.setParameter("insertValue", insertValue);
			query2.executeUpdate();
			
		}
		
		session.getTransaction().commit();
		session.close();
		System.out.println(hasEnoughStocks);
		return hasEnoughStocks;
	}
	

}
