package org.openjfx.mavenfx.tableStrategy;

import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.trades.TradeDbMethods;
import org.openjfx.mavenfx.user.UserSingleton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SubmitedTable<T> extends MainTable {

	private ObservableList<Trade> submitedList;
	
	@Override
	public void createTable() {
		TableColumn<Trade,String> companyCol = new TableColumn<Trade,String>("Company");
		companyCol.setCellValueFactory(new PropertyValueFactory("company"));
		TableColumn<Trade,String> buyOrSellCol = new TableColumn<Trade,String>("Buy/Sell");
		buyOrSellCol.setCellValueFactory(new PropertyValueFactory("buyOrSell"));
		TableColumn<Trade,String> orderTimeCol = new TableColumn<Trade,String>("Order Time");
		orderTimeCol.setCellValueFactory(new PropertyValueFactory("orderTime"));
		TableColumn<Trade,Double> priceCol = new TableColumn<Trade,Double>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory("price"));
		TableColumn<Trade,Double> totalPriceCol = new TableColumn<Trade,Double>("Total Price");
		totalPriceCol.setCellValueFactory(new PropertyValueFactory("totalPrice"));
		TableColumn<Trade,Integer> amountCol = new TableColumn<Trade,Integer>("Amount");
		amountCol.setCellValueFactory(new PropertyValueFactory("amount"));
		TableColumn<Trade,Integer> amountToTradeCol = new TableColumn<Trade,Integer>("Amount To Trade");
		amountToTradeCol.setCellValueFactory(new PropertyValueFactory("amountToTrade"));
		TableColumn<Trade,String> statusCol = new TableColumn<Trade,String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory("tradeStatus"));
		getColumns().setAll(companyCol,buyOrSellCol,orderTimeCol,priceCol,totalPriceCol,amountCol,amountToTradeCol,statusCol);
		
	}


	@Override
	public void setList(String company) {
		TradeDbMethods tdm = new TradeDbMethods();
		submitedList = FXCollections.observableArrayList(tdm.getSubmitedTradeList(company, UserSingleton.getUser().getId()));
		this.setItems(submitedList);
		
	}
	
	
	
}
