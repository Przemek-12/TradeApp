package org.openjfx.mavenfx.panes;

import org.openjfx.mavenfx.App;
import org.openjfx.mavenfx.tableStrategy.*;
import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.urlRequest.URLRequest;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.*;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListsPane extends VBox{
	
	private static SubmitedTable<Trade> submitedTable;
	private static CompletedTable<Trade> completedTable; 
	private static BuyTable<Trade> buyTable;
	private static SellTable<Trade> sellTable;
	public static LineChart<String,Number> chart;
	

	public void createListsPane() {
		
		//panes with recent buy and sell transactions and linechart
		HBox buyAndSellTables = new HBox();
		
			VBox buyFlow = new VBox();
			Label buyLab = new Label("Recent Buy Orders");
			buyTable = new BuyTable<Trade>();
			buyTable.createTable();			
			buyFlow.getChildren().addAll(buyLab,buyTable);
			
			
			VBox stockChartPane = new VBox();
				stockChartPane.setPrefSize(800, 600);
				StockChart stockChart = new StockChart(); 		
				chart = stockChart.createChart();
				Button refreshChartBtn = new Button("Refresh Chart");
			stockChartPane.getChildren().addAll(chart,refreshChartBtn);
				
			
			VBox sellFlow = new VBox();
			Label sellLab = new Label("Recent Sell Orders");
			sellTable = new SellTable<Trade>();
			sellTable.createTable();
			sellFlow.getChildren().addAll(sellLab,sellTable);
			
		buyAndSellTables.getChildren().addAll(buyFlow, stockChartPane, sellFlow);
			
		
		
		//trades submited by user
		VBox ordersSubmited = new VBox();
			ordersSubmited.prefWidth(1200);
			ordersSubmited.setAlignment(Pos.CENTER);
			Label subLab = new Label("Your Submited Orders");
			submitedTable = new SubmitedTable<Trade>();
			submitedTable.createTable();
			submitedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ordersSubmited.getChildren().addAll(subLab, submitedTable);
		
				
		
		//trades completed by all users
		VBox ordersCompleted = new VBox();
			ordersCompleted.prefWidth(1200);
			ordersCompleted.setAlignment(Pos.CENTER);
			Label comLab = new Label("Completed Orders");
			completedTable = new CompletedTable<Trade>();
			completedTable.createTable();
			completedTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ordersCompleted.getChildren().addAll(comLab, completedTable);
		
		TableDataSetter tds = new TableDataSetter();
		tds.setCompany("gold");
		tds.setTableData();
		
		//adding children to listsPane
		this.getChildren().addAll(buyAndSellTables, ordersSubmited, ordersCompleted);

		
		refreshChartBtn.setOnAction(event->{
			new URLRequest().setChartData();
		});
		
		
	}
	
	
	public SubmitedTable<Trade> getSubmitedTable() {
		return submitedTable;
	}

	public CompletedTable<Trade> getCompletedTable() {
		return completedTable;
	}

	public BuyTable<Trade> getBuyTable() {
		return buyTable;
	}

	public SellTable<Trade> getSellTable() {
		return sellTable;
	}
	
	public LineChart<String,Number> getChart() {
		return chart;
	}
	
	
}
