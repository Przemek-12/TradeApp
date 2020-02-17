package mavenfx;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.openjfx.mavenfx.tableStrategy.TableDataSetter;
import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.trades.TradeDBTransactions;
import org.openjfx.mavenfx.trades.TradeDbMethods;
import org.openjfx.mavenfx.user.User;
import org.openjfx.mavenfx.user.databaseMethods;

public class TradeDBTransactionsTest {

	@Test
	public void transactionTest() {
		
		databaseMethods dm= new databaseMethods();
		
		User user1 = new User("testn", "testp", "teste");
		dm.createUser(user1);
		
		User user2 = new User("testn2", "testp2", "teste2");
		dm.createUser(user2);
		
		LocalDateTime ldt = LocalDateTime.now();
		TradeDbMethods tdm = new TradeDbMethods();
		Trade trade1 = new Trade("testCompany", ldt, "sell", user1.getId(), 123.0,
				123.0, 1, "submited", 123);
		Trade trade2 = new Trade("testCompany", ldt, "buy", user2.getId(), 123.0,
				123.0, 1, "submited", 123);
		tdm.saveTrade(trade1);
		tdm.saveTrade(trade2);
		
		new TableDataSetter().setCompany("testCompany");
		
		new TradeDBTransactions();
		
		assertEquals(tdm.getTrade(trade1.getTradeId()).getTradeStatus(), "completed");
		assertEquals(tdm.getTrade(trade2.getTradeId()).getTradeStatus(), "completed");
		
		
		dm.deleteUser(user1);
		dm.deleteUser(user2);
	}
}
