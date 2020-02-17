package org.openjfx.mavenfx.tableStrategy;

import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.trades.TradeDbMethods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings("rawtypes")
public class CompletedTable<T> extends MainTable {

	private ObservableList<Trade> completedList;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		TableColumn<Trade,String> statusCol = new TableColumn<Trade,String>("Status");
		statusCol.setCellValueFactory(new PropertyValueFactory("tradeStatus"));
		getColumns().setAll(companyCol,buyOrSellCol,orderTimeCol,priceCol,totalPriceCol,amountCol,statusCol);
		
	}
	
	@SuppressWarnings("unchecked")
	public void setList(String company) {
		TradeDbMethods tdm = new TradeDbMethods();
		completedList = FXCollections.observableArrayList(tdm.getCompletedTradeList(company));
		this.setItems(completedList);
	}
}
