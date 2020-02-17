package org.openjfx.mavenfx.tableStrategy;

import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.trades.TradeDbMethods;
import org.openjfx.mavenfx.user.UserSingleton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuyTable<T> extends MainTable {

	private ObservableList<Trade> buyList;
	
	@Override
	public void createTable() {
		
		TableColumn<Trade,Double> priceCol = new TableColumn<Trade,Double>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory("price"));
		TableColumn<Trade,Double> totalPriceCol = new TableColumn<Trade,Double>("Total Price");
		totalPriceCol.setCellValueFactory(new PropertyValueFactory("totalPrice"));
		TableColumn<Trade,Integer> amountCol = new TableColumn<Trade,Integer>("Amount");
		amountCol.setCellValueFactory(new PropertyValueFactory("amount"));
		getColumns().setAll(priceCol,totalPriceCol,amountCol);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setList(String company) {
		TradeDbMethods tdm = new TradeDbMethods();
		buyList = FXCollections.observableArrayList(tdm.getBuyList(company));
		this.setItems(buyList);
		
	}

}
