package mavenfx;



import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.trades.TradeDbMethods;
import org.openjfx.mavenfx.user.User;
import org.openjfx.mavenfx.user.databaseMethods;

public class TradeDbMethodsTest {

	public static User user;
	
	public static databaseMethods dm;
	
	public static LocalDateTime ldt;
	
	public static TradeDbMethods tdm;
	
	public static Trade trade;
	public static Trade trade2;
	public static Trade trade3;
	public static Trade trade4;
	
	@BeforeClass
	public static void setup() {
		dm= new databaseMethods();
		user = new User("testn", "testp", "teste");
		dm.createUser(user);
		ldt = LocalDateTime.now();
		tdm = new TradeDbMethods();
		 trade = new Trade("testCompany", ldt, "buyOrSell", user.getId(), 123.0,
				123.0, 1, "completed", 123);
		 trade2 = new Trade("testCompany", ldt, "buy", user.getId(), 123.0,
				123.0, 1, "submited", 123);
		 trade3 = new Trade("testCompany", ldt, "sell", user.getId(), 123.0,
				123.0, 1, "submited", 123);
		 trade4 = new Trade("testCompany", ldt, "buyOrSell", user.getId(), 123.0,
					123.0, 1, "tradeStatus", 123);
		
		tdm.saveTrade(trade);
		tdm.saveTrade(trade2);
		tdm.saveTrade(trade3);
		tdm.saveTrade(trade4);
	}
	
	@Test
	public void saveGetTradeTest() {
		
		Trade t = new Trade("testCompany", ldt, "buyOrSell", user.getId(), 123.0,
				123.0, 1, "tradeStatus", 123);
		
		tdm.saveTrade(t);
		Trade trade2 = tdm.getTrade(t.getTradeId());

		assertTrue(t.equals(trade2));
		
		
	}
	
	@Test
	public void updateTradeTest() {
		
		trade4.setCompany("updateCompany");
		tdm.updateTrade(trade4);
		Trade trade2 = tdm.getTrade(trade4.getTradeId());

		assertEquals(trade2.getCompany(),"updateCompany" );
		
	}
	
	@Test
	public void completedTableTest() {
		
		List<Trade> list =  new TradeDbMethods().getCompletedTradeList("testCompany");
		
		for(Trade t:list) {
			assertEquals(t.getTradeStatus(),"completed");
		}
		
	}

	@Test
	public void submitedTableTest() {
		
		List<Trade> list =  new TradeDbMethods().getSubmitedTradeList("testCompany", user.getId());
		
		for(Trade t:list) {
			assertEquals(t.getTradeStatus(),"submited");
		}
		
	}
	
	@Test
	public void sellTableTest() {
		
		List<Trade> list =  new TradeDbMethods().getSellList("testCompany");
		
		for(Trade t:list) {
			assertEquals(t.getTradeStatus(),"submited");
			assertEquals(t.getBuyOrSell(),"sell");
		}
		
	}
	
	@Test
	public void buyTableTest() {
		
		List<Trade> list =  new TradeDbMethods().getBuyList("testCompany");
		
		for(Trade t:list) {
			assertEquals(t.getTradeStatus(),"submited");
			assertEquals(t.getBuyOrSell(),"buy");
		}
		
		
	}
	
	@Test
	public void userTradeListTest() {
		
		List<Trade> list =  new TradeDbMethods().getUserTradeList(user.getId());
		
		for(Trade t:list) {
			assertEquals(t.getIdUsers(),user.getId());
		}
		
	}

	
	@AfterClass
	public static void clean() {
		//when user is deleted all his trades are deleted
		dm.deleteUser(user);
	}
	
	
	
}
