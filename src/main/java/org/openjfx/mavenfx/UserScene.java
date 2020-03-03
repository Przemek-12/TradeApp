package org.openjfx.mavenfx;

import org.openjfx.mavenfx.tableStrategy.CompletedTable;
import org.openjfx.mavenfx.tableStrategy.HistoryTable;
import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.trades.TradeDbMethods;
import org.openjfx.mavenfx.trades.UserStock;
import org.openjfx.mavenfx.trades.UserStockDbMethods;
import org.openjfx.mavenfx.user.UserSingleton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class UserScene extends Scene {

	@SuppressWarnings({ "exports","rawtypes", "unchecked" })
	public UserScene(FlowPane flowRoot, int x, int y) {
		super(flowRoot, x, y);
		
		this.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		
		HBox hbox = new HBox();
		Button stockBtn = new Button("Stock Exchange");
		hbox.getChildren().addAll(stockBtn);
		
		
		
		VBox stockBox = new VBox();
			TableView<UserStock> stockTable = new TableView<UserStock>();
			UserStockDbMethods usm = new UserStockDbMethods();
			ObservableList<UserStock> stockList = FXCollections.observableArrayList(usm.getUserStockList(UserSingleton.getUser().getId()));
			stockTable.setItems(stockList);
			
			TableColumn<UserStock,Integer> gasCol = new TableColumn<UserStock,Integer>("Gas");
			gasCol.setCellValueFactory(new PropertyValueFactory("gas"));
			TableColumn<UserStock,Integer> goldCol = new TableColumn<UserStock,Integer>("Gold");
			goldCol.setCellValueFactory(new PropertyValueFactory("gold"));
			TableColumn<UserStock,Integer> microsoftCol = new TableColumn<UserStock,Integer>("Microsoft");
			microsoftCol.setCellValueFactory(new PropertyValueFactory("microsoft"));
			TableColumn<UserStock,Integer> sonyCol = new TableColumn<UserStock,Integer>("Sony");
			sonyCol.setCellValueFactory(new PropertyValueFactory("sony"));
			
			stockTable.getColumns().setAll(gasCol,goldCol,microsoftCol,sonyCol);
			stockTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			stockBox.prefWidth(1200);
		stockBox.getChildren().addAll(stockTable);
		
		
		//table with all transactions made by this user
		VBox ordersCompleted = new VBox();
			Label historyTabLab = new Label("Transactions History");
			HistoryTable historyTable = new HistoryTable<Trade>();
			historyTable.createTable();
			historyTable.setList("all");
			historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ordersCompleted.getChildren().addAll(historyTabLab, historyTable);
		
		
		flowRoot.setId("userScene");
		flowRoot.setOrientation(Orientation.HORIZONTAL);
		flowRoot.setAlignment(Pos.TOP_CENTER);
		flowRoot.getChildren().addAll(hbox,stockBox,ordersCompleted);
		
		stockBtn.setOnAction(event->{
			App.stage.setScene(new SceneSetter().setMenuScene());
			
		});
		
	}

}
