package org.openjfx.mavenfx;

import java.time.LocalDateTime;
import org.openjfx.mavenfx.panes.*;
import org.openjfx.mavenfx.tableStrategy.TableDataSetter;
import org.openjfx.mavenfx.threads.AppThreads;
import org.openjfx.mavenfx.trades.Trade;
import org.openjfx.mavenfx.trades.TradeDbMethods;
import org.openjfx.mavenfx.trades.UserStockDbMethods;
import org.openjfx.mavenfx.urlRequest.URLRequest;
import org.openjfx.mavenfx.user.UserSingleton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


@SuppressWarnings("exports")
public class MenuScene extends Scene {

	private static boolean threadsAreRunning;
	
	public static TextField currentPriceValue;
	
	public static TextField messageLabel;
	
	public static TextField connection;
	
	
	public MenuScene(ScrollPane flowRoot, int x, int y) {
		super(flowRoot, x, y);
		
		this.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		
		//main contains scrollpane that scrolls menuscene
		FlowPane main = new FlowPane();
		main.setId("menuScene");
		
		
		//Buttons with companies at the top
		HBox hbox = new HBox();
			hbox.setSpacing(20);
			hbox.setFillHeight(true);
			hbox.prefWidthProperty().bind(App.stage.widthProperty());
			hbox.prefHeight(50);
			hbox.setId("stockBtnHbox");	
			hbox.setAlignment(Pos.TOP_CENTER);
			
			Button myAccountBtn = new Button(UserSingleton.getUser().getUserName());
			Button goldBtn = new Button("Gold");
			Button gasBtn = new Button("Gas");
			Button microsoftBtn = new Button("Microsoft");
			Button sonyBtn = new Button("Sony");
		hbox.getChildren().addAll(myAccountBtn,goldBtn,gasBtn,microsoftBtn,sonyBtn);
		
		
		
		//submitTrade contains elements for submiting trades into database
		VBox vbox = new VBox();
			HBox submitTrade = new HBox();
				Button buyBtn = new Button("BUY");
				Button sellBtn = new Button("SELL");
				Label priceLab = new Label("Price");
				TextField priceField = new TextField("0");
				Label amountLab = new Label("Amount");
				TextField amountField = new TextField("0");
				Label totalPriceLab = new Label("Total Price:");
				TextField totalPriceField = new TextField();
				totalPriceField.setDisable(true);
				
				Label currentPrice = new Label("Current Price:");
				currentPriceValue = new TextField();
				
			submitTrade.getChildren().addAll(currentPrice,currentPriceValue,priceLab,priceField,amountLab,amountField,totalPriceLab,totalPriceField,buyBtn,sellBtn);
			
			HBox messageBox = new HBox();
				Label label = new Label("messages:");
				messageLabel = new TextField();	
				messageLabel.setDisable(true);
				connection = new TextField();
				connection.setDisable(true);
			messageBox.getChildren().addAll(label, messageLabel,connection);
		vbox.getChildren().addAll(submitTrade, messageBox);
		
		
		//area with tables
		ListsPane listsPane = new ListsPane();
		listsPane.createListsPane();
		
		
		
		main.getChildren().addAll(hbox, vbox, listsPane);
		main.setOrientation(Orientation.HORIZONTAL);
		main.setAlignment(Pos.CENTER);
		main.prefWidthProperty().bind(App.stage.widthProperty());
		main.prefHeightProperty().bind(App.stage.heightProperty());
		
		flowRoot.setContent(main);
		
		
		URLRequest urlr = new URLRequest();
		urlr.setURLCompany("GLD");
		urlr.setChartData();
		
		// Starts all threads
		if(!threadsAreRunning) {
			threadsAreRunning=true;	
			new AppThreads();
		}
		
		
		
		priceField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	
		        //System.out.println(" Text Changed to  " + newValue + ")\n");
		    	
		    	Double total = Double.parseDouble(priceField.getText())*Double.parseDouble(amountField.getText());
		    	totalPriceField.setText(String.valueOf(total));
		    }
		});
		
		amountField.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {

		    	Double total = Double.parseDouble(amountField.getText())*Double.parseDouble(priceField.getText());
		    	totalPriceField.setText(String.valueOf(total));
		    }
		});
		
		
		sellBtn.setOnAction(event->{
			
			//transaction is commited of user has proper amiunt of stocks
			UserStockDbMethods usdm = new UserStockDbMethods();
			
			if( usdm.setSellStock(UserSingleton.getUser().getId(),TableDataSetter.company,Integer.parseInt(amountField.getText()))==true ) {
				System.out.println("qwerwtreyrteuyr");
				LocalDateTime ldt = LocalDateTime.now();
				Trade trade = new Trade( TableDataSetter.company, ldt, "sell", UserSingleton.getUser().getId(), Double.parseDouble(priceField.getText()), 
						Double.parseDouble(amountField.getText())*Double.parseDouble(priceField.getText()), Integer.parseInt(amountField.getText()), "submited", Integer.parseInt(amountField.getText()));
				
				TradeDbMethods tdm = new TradeDbMethods();
				tdm.saveTrade(trade);
			}
			
			
		});
		
		buyBtn.setOnAction(event->{
			LocalDateTime ldt = LocalDateTime.now();
			Trade trade = new Trade( TableDataSetter.company, ldt, "buy", UserSingleton.getUser().getId(), Double.parseDouble(priceField.getText()), 
					Double.parseDouble(amountField.getText())*Double.parseDouble(priceField.getText()), Integer.parseInt(amountField.getText()), "submited", Integer.parseInt(amountField.getText()));
			
			TradeDbMethods tdm = new TradeDbMethods();
			tdm.saveTrade(trade);
			UserStockDbMethods usdm = new UserStockDbMethods();
			usdm.setBuyStock(UserSingleton.getUser().getId(),TableDataSetter.company,Integer.parseInt(amountField.getText()));
		});
		
		
		
		myAccountBtn.setOnAction(event->{
			App.stage.setScene(new SceneSetter().setUserScene());
		});
		
		goldBtn.setOnAction(event->{			
			TableDataSetter tds = new TableDataSetter();
			tds.setCompany("gold");
			tds.setTableData();
			URLRequest url = new URLRequest();
			url.setURLCompany("GLD");
			url.setChartData();
		});
		
		gasBtn.setOnAction(event->{
			TableDataSetter tds = new TableDataSetter();
			tds.setCompany("gas");
			tds.setTableData();
			URLRequest url = new URLRequest();
			url.setURLCompany("NG=F");
			url.setChartData();
		});
		
		microsoftBtn.setOnAction(event->{		
			TableDataSetter tds = new TableDataSetter();
			tds.setCompany("microsoft");
			tds.setTableData();
			URLRequest url = new URLRequest();
			url.setURLCompany("MSFT");
			url.setChartData();
		});
		
		sonyBtn.setOnAction(event->{		
			TableDataSetter tds = new TableDataSetter();
			tds.setCompany("sony");
			tds.setTableData();
			URLRequest url = new URLRequest();
			url.setURLCompany("SNE");
			url.setChartData();
		});
		
	}
	
	
}
