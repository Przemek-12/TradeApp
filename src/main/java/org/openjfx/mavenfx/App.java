package org.openjfx.mavenfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.openjfx.mavenfx.URLconnection.ConnectionTester;
import org.openjfx.mavenfx.session.HibernateSession;
import org.openjfx.mavenfx.user.User;


public class App extends Application {

    public static Stage stage;
   
    @Override
    public void start(Stage stage) {
    	
    	try {
        
        //loginScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(new SceneSetter().setLoginScene());
        stage.show();
        
        App.stage=stage;     
        
        System.out.println(User.class.getName());
        System.out.println(User.class);
        
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    	
    }

   
    public static void main(String[] args) {
	    launch();
	}

}