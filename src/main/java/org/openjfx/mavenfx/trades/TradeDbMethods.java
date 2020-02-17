package org.openjfx.mavenfx.trades;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openjfx.mavenfx.session.HibernateSession;
import org.openjfx.mavenfx.user.User;
import org.openjfx.mavenfx.user.UserSingleton;

public class TradeDbMethods {
	

	public void saveTrade(Trade trade) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		session.save(trade);
		session.getTransaction().commit();
		session.close();
	}
	
	
	public void updateTrade(Trade trade) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		session.update(trade);
		session.getTransaction().commit();
		session.close();
	}
	
	
	public Trade getTrade(int id) {
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Trade trade = (Trade)session.get(Trade.class, id);
		session.getTransaction().commit();
		session.close();
		return trade;
	}
	
	//list of trades made by all users - completedTable
	@SuppressWarnings("unchecked")
	public List<Trade> getCompletedTradeList(String company){
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Trade where company=:company and tradeStatus=:status");
		query.setParameter("company", company);
		query.setParameter("status", "completed");
		//query.setMaxResults(20);
		List<Trade> tradeList = query.list();
		session.getTransaction().commit();
		session.close();
		Collections.reverse(tradeList);
		return tradeList;
	}
	
	
	//submitedTable of trades by user
	@SuppressWarnings("unchecked")
	public List<Trade> getSubmitedTradeList(String company, int idUsers){
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Trade where company=:company and idUsers=:idUsers and tradeStatus=:status");
		query.setParameter("company", company);
		query.setParameter("idUsers", idUsers);
		query.setParameter("status", "submited");
		//query.setMaxResults(20);
		List<Trade> tradeList = query.list();
		session.getTransaction().commit();
		session.close();
		Collections.reverse(tradeList);
		return tradeList;
	}
	
	
	//SellTable, orders submited waiting to be baught
	public List<Trade> getSellList(String company){
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Trade where company=:company and buyOrSell=:buyOrSell and tradeStatus=:status");
		query.setParameter("company", company);
		query.setParameter("buyOrSell", "sell");
		query.setParameter("status", "submited");
		//query.setMaxResults(20);
		List<Trade> tradeList = query.list();
		session.getTransaction().commit();
		session.close();
		Collections.reverse(tradeList);
		return tradeList;
	}
	
	
	//orders submited waited to be sell
	public List<Trade> getBuyList(String company){
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Trade where company=:company and buyOrSell=:buyOrSell and tradeStatus=:status");
		query.setParameter("company", company);
		query.setParameter("buyOrSell", "buy");
		query.setParameter("status", "submited");
		//query.setMaxResults(20);
		List<Trade> tradeList = query.list();
		session.getTransaction().commit();
		session.close();
		Collections.reverse(tradeList);
		return tradeList;
	}
	
	
	
	//list of trades made by this user - historyTable
	public List<Trade> getUserTradeList(int idUser){
		Session session = HibernateSession.createSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Trade> tradeList = session.createQuery("from Trade T where T.idUsers="+String.valueOf(idUser)).list();
		session.getTransaction().commit();
		session.close();
		Collections.reverse(tradeList);
		return tradeList;
	}
	
	
}
