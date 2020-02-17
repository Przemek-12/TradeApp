package org.openjfx.mavenfx.urlRequest;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openjfx.mavenfx.MenuScene;
import org.openjfx.mavenfx.panes.ListsPane;
import org.openjfx.mavenfx.tableStrategy.StockChart;

public class URLRequest {
	
	private static String company="GLD";

	private static ArrayList<String> keys;
	
	private static JSONObject jobj2;
	
	@SuppressWarnings("unchecked")
	public URLRequest() {
		
		 try{
	            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+company+"&apikey=B7JFREVMFYQ14ZFS");
	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.connect();
	            int responsecode = conn.getResponseCode();

	            if(responsecode != 200){
	                throw new RuntimeException("HttpResponseCode: " +responsecode);
	            }
	            else
	            {
	                String inline="";
	                Scanner sc = new Scanner(url.openStream());
	                while(sc.hasNext()){
	                    inline+=sc.nextLine();
	                }
	                //System.out.println("\nJSON data in string format");
	                System.out.println(inline);
	                sc.close();
	               
	                JSONParser parser = new JSONParser();
	                
	                //parsing json into string
	                JSONObject jobj = (JSONObject)parser.parse(inline);
	                
	                //jobj contains meta data object and daily object
	                JSONObject jobj1 = (JSONObject) jobj.get("Meta Data");
	                jobj2 = (JSONObject) jobj.get("Time Series (Daily)");
	                
	                // jobj3 contains data from last refreshed date
	                JSONObject jobj3 = (JSONObject) jobj2.get(jobj1.get("3. Last Refreshed"));
	                
	                // sets current price to label in menuscene
	                MenuScene.currentPriceValue.setText((String) jobj3.get("1. open"));
	                
	            
	                // set cannot be sorted so data must be put into arraylist
					Set<String> values = jobj2.keySet();
	                Iterator<String> iterator = values.iterator();
					keys = new ArrayList();
	                
	                while(iterator.hasNext()){
	                    keys.add(iterator.next());
	                }
	                
	                Collections.sort(keys);
        
	                
	            }
	            
	        }catch(Exception e){}
		 
	}
	
	public void setURLCompany(String company) {
		this.company=company;
	}
	
	public void setChartData() {
		ListsPane.chart.setData(StockChart.setSeries(keys, jobj2));
	}
	
}
