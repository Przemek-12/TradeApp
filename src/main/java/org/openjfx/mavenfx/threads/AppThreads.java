package org.openjfx.mavenfx.threads;

import org.openjfx.mavenfx.URLconnection.ConnectionTester;
import org.openjfx.mavenfx.tableStrategy.TableDataSetter;
import org.openjfx.mavenfx.trades.TradeDBTransactions;
import org.openjfx.mavenfx.urlRequest.URLRequest;

import javafx.concurrent.Task;

//Daemon thread is a low priority thread that runs in background to perform tasks such as garbage collection.

//Properties:

//They can not prevent the JVM from exiting when all the user threads finish their execution.
//JVM terminates itself when all user threads finish their execution
//If JVM finds running daemon thread, it terminates the thread and after that shutdown itself. JVM does not care whether Daemon thread is running or not.
//It is an utmost low priority thread.

public class AppThreads {

	public AppThreads() {
		tableDataThread();
		urlAPIThread();
		tradeTransactionsThread();
		connectionThread();
	}
	
	
	// updates data from database into tables in listsPane
	public void tableDataThread() {
		
		 Task<Void> tableTask = new Task<Void>(){
			  
		        @Override
		        protected Void call()  {
		        	int k=0;
		        	while(k<1) {
		        		try {
							Thread.sleep(5000);
							new TableDataSetter().setTableData();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		        	}
		            return null;
		        }
		    };
		    
		  Thread t1 = new Thread(tableTask);
		  t1.setDaemon(true);
		  t1.start();
		  
	}
	
	
	// fetching json object from API, actual stock price, chart
	public void urlAPIThread() {
		
		 Task<Void> urlTask = new Task<Void>(){
			  
		        @Override
		        protected Void call()  {
		        	int k=0;
		        	while(k<1) {
		        		try {
							new URLRequest();
							Thread.sleep(30000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		        	}
		            return null;
		        }
		    };
		    
		  Thread t2 = new Thread(urlTask);
		  t2.setDaemon(true);
		  t2.start();
		  
	}
	
	
	// commiting buy and sell transactions in database, initializing class TradeDBTransactions
	public void tradeTransactionsThread() {
		
		 Task<Void> tradeTask = new Task<Void>(){
			  
		        @Override
		        protected Void call()  {
		        	int k=0;
		        	while(k<1) {
		        		try {
		        			Thread.sleep(20000);
							new TradeDBTransactions();
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		        	}
		            return null;
		        }
		    };
		    
		  Thread t3 = new Thread(tradeTask);
		  t3.setDaemon(true);
		  t3.start();
		  
	}
	
	
	public void connectionThread() {
		
		 Task<Void> connectionTask = new Task<Void>(){
			  
		        @Override
		        protected Void call()  {
		        	int k=0;
		        	while(k<1) {
		        		try {
							new ConnectionTester();
							Thread.sleep(10000);
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		        	}
		            return null;
		        }
		    };
		    
		  Thread t4 = new Thread(connectionTask);
		  t4.setDaemon(true);
		  t4.start();
		  
	}
	
}
