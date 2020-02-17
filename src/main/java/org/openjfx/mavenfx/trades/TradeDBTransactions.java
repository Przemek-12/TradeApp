package org.openjfx.mavenfx.trades;

import java.util.*;
import java.util.Collections;

import org.hibernate.Session;
import org.openjfx.mavenfx.session.HibernateSession;
import org.openjfx.mavenfx.tableStrategy.TableDataSetter;
import org.openjfx.mavenfx.user.UserSingleton;

// class responsible for trading based on buy and sell orders, runs every 15 seconds initialized by thread
public class TradeDBTransactions {

	public TradeDBTransactions() {
		
		TradeDbMethods tdm = new TradeDbMethods();
		// list of submitted sell transactions
		ArrayList<Trade> sellList = (ArrayList<Trade>) tdm.getSellList(TableDataSetter.company);
		Collections.reverse(sellList);
		
		//list of submitted buy transactions
		ArrayList<Trade> buyList = (ArrayList<Trade>) tdm.getBuyList(TableDataSetter.company);
		Collections.reverse(buyList);
		
		Iterator<Trade> sellIterator = sellList.iterator();
		Iterator<Trade> buyIterator; 
		
		
		//id of user that is currently checked
		int currentIdUsers;
		// amount of stocks that left to trade by this user transaction
		int sellAmountToTrade;
		// price that user sells
		Double price;

		
		//iteration through sellList, for each element goes iteration of buyList
		while(sellIterator.hasNext()) {

			Trade sellTrade = sellIterator.next();
			currentIdUsers = sellTrade.getIdUsers();
			sellAmountToTrade = sellTrade.getAmountToTrade();
			price = sellTrade.getPrice();
			buyIterator = buyList.iterator();
		
			while(buyIterator.hasNext()) {
				
				Trade buyTrade= buyIterator.next();
				int buyAmountToTrade = buyTrade.getAmountToTrade();
				int buyIdUsers = buyTrade.getIdUsers();
				Double buyPrice = buyTrade.getPrice();
				
				
				//if user is different than current user and price is the same
				if(buyIdUsers!=currentIdUsers && buyPrice.equals(price)) {
					
					if(sellAmountToTrade<buyAmountToTrade) {
						
						buyAmountToTrade=buyAmountToTrade-sellAmountToTrade;
						sellAmountToTrade=0;
						sellTrade.setAmountToTrade(sellAmountToTrade);
						sellTrade.setTradeStatus("completed");
						tdm.updateTrade(sellTrade);
						buyTrade.setAmountToTrade(buyAmountToTrade);
						tdm.updateTrade(buyTrade);
						
					}
					
					else if(sellAmountToTrade>buyAmountToTrade) {
					
						sellAmountToTrade=sellAmountToTrade-buyAmountToTrade;
						buyAmountToTrade=0;
						sellTrade.setAmountToTrade(sellAmountToTrade);
						tdm.updateTrade(sellTrade);
						buyTrade.setTradeStatus("completed");
						buyTrade.setAmountToTrade(buyAmountToTrade);
						tdm.updateTrade(buyTrade);
						
					}
					
					else if(sellAmountToTrade==buyAmountToTrade) {
					
						buyAmountToTrade=0;
						sellAmountToTrade=0;
						sellTrade.setAmountToTrade(sellAmountToTrade);
						sellTrade.setTradeStatus("completed");
						tdm.updateTrade(sellTrade);
						buyTrade.setTradeStatus("completed");
						buyTrade.setAmountToTrade(buyAmountToTrade);
						tdm.updateTrade(buyTrade);
						
					}
					
				}
				
			}
			
			
		}
		
		
		
	}
	
}
