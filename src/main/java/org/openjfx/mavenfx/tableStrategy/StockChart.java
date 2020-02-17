package org.openjfx.mavenfx.tableStrategy;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.openjfx.mavenfx.panes.ListsPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class StockChart {
	

	private static ObservableList<XYChart.Series<String, Number>> observableList;
		
	
	public static ObservableList<XYChart.Series<String, Number>> setSeries(ArrayList<String> keys, JSONObject jobj2) {
				
		observableList = FXCollections.observableArrayList();
		
		ObservableList<XYChart.Data<String, Number>> series = FXCollections.observableArrayList();

			for(String s: keys) {
			 JSONObject tempjobj = (JSONObject) jobj2.get(s);
			 series.add(new XYChart.Data<String,Number>(s, Double.parseDouble( (String) tempjobj.get("1. open"))) ); 
			}
					
			observableList.add(new XYChart.Series("Stock price",series));
			
			System.out.println(observableList);			
			
			return observableList;
	}
	
	
	
	public LineChart<String,Number> createChart() {
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Date");
		Axis yAxis = new NumberAxis();
		yAxis.setLabel("Stock Price");
		
		LineChart<String,Number> stockChart = new LineChart<String,Number>(xAxis, yAxis);
		stockChart.setAnimated(false);
		stockChart.setPrefSize(800,600);
		return stockChart;
	}
	
	
	
	
}
