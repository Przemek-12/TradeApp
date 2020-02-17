package org.openjfx.mavenfx.URLconnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.openjfx.mavenfx.MenuScene;

public class ConnectionTester {
	
	
	public ConnectionTester() {
				
			try {
				URL url = new URL("https://www.google.pl/");
				URLConnection connection = url.openConnection(); 
	            connection.connect(); 			    
	            
			    MenuScene.connection.setText("Internet connected");
     
			} catch (MalformedURLException e) {
				MenuScene.connection.setText("Internet not connected");
				e.printStackTrace();
			} catch (IOException e) {
				MenuScene.connection.setText("Internet not connected");
				e.printStackTrace();
			} 		
		
	}
}
