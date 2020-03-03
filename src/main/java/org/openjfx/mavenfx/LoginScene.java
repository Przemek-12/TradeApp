package org.openjfx.mavenfx;

import org.hibernate.HibernateException;
import org.openjfx.mavenfx.user.User;
import org.openjfx.mavenfx.user.UserSingleton;
import org.openjfx.mavenfx.user.databaseMethods;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.GridPane;

public class LoginScene extends Scene{
	

	public LoginScene(GridPane gridRoot,int x,int y) throws HibernateException, Exception {
		super(gridRoot,x,y);
		
		this.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		
		Label namelab = new Label("Username");
		Label passlab = new Label("Password");
		Label valid = new Label();
		Label connection = new Label();
		
		TextField tfname = new TextField();
		PasswordField pf = new PasswordField();
		
		Button loginBtn = new Button("LogIn");
		loginBtn.setId("Btn");
		Button registerBtn = new Button("Register");
		registerBtn.setId("registerBtn");
		
		gridRoot.addRow(1, namelab, tfname);
		gridRoot.addRow(2, passlab, pf);
		gridRoot.addRow(3, valid);
		gridRoot.addRow(4, loginBtn, registerBtn);
		gridRoot.addRow(5, connection);
		gridRoot.setAlignment(Pos.CENTER);
		gridRoot.setId("loginScene");
		
		registerBtn.setOnAction(event->{
			App.stage.setScene(new SceneSetter().setRegisterScene());
		});
		
		
		loginBtn.setOnAction(event->{
			try {
			
			databaseMethods dm = new databaseMethods();	
			User u = dm.getUser(tfname.getText(), pf.getText());
			
				if(u==null) {
					System.out.println("no object");
					valid.setText("Wrong username or password");
				}
				
				else {
					UserSingleton.setUser(u);			
					App.stage.setScene(new SceneSetter().setMenuScene());				
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
		
			
				
		
		
		
		
		
	}

}
