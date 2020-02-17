package org.openjfx.mavenfx.tableStrategy;

import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.trades.TradeDbMethods;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SellTable<T> extends MainTable {

	private ObservableList<Trade> sellList;
	
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

	
	@Override
	public void setList(String company) {
		TradeDbMethods tdm = new TradeDbMethods();
		sellList = FXCollections.observableArrayList(tdm.getSellList(company));
		this.setItems(sellList);
		
	}

}
