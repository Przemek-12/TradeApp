package org.openjfx.mavenfx;

import org.hibernate.Session;
import org.openjfx.mavenfx.session.HibernateSession;
import org.openjfx.mavenfx.trades.UserStock;
import org.openjfx.mavenfx.trades.UserStockDbMethods;
import org.openjfx.mavenfx.user.User;
import org.openjfx.mavenfx.user.databaseMethods;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class RegisterScene extends Scene{

	public RegisterScene(GridPane gridRoot, int x , int y) {
		super(gridRoot, x, y);
		
		this.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		
		Label namelab = new Label("Username");
		Label passlab = new Label("Password");
		Label emaillab = new Label("Email");
		Label valid = new Label();
		
		TextField tfname = new TextField();
		TextField tfemail = new TextField();
		PasswordField pf = new PasswordField();
		
		Button submitBtn = new Button("Register");
		submitBtn.setId("registerBtn");
		
		gridRoot.addRow(1, emaillab, tfemail);
		gridRoot.addRow(2, namelab, tfname);
		gridRoot.addRow(3, passlab, pf);
		gridRoot.addRow(4, valid);
		gridRoot.addRow(5, submitBtn);
		gridRoot.setAlignment(Pos.CENTER);
		gridRoot.setId("registerScene");
		
		submitBtn.setOnAction(event->{
			try {
			
			User user = new User(tfname.getText(), pf.getText(), tfemail.getText());
			databaseMethods dm = new databaseMethods();	
			
			User u1 = dm.getUsername(tfname.getText());
			User u2 = dm.getEmail(tfemail.getText());
			
				if( u1 !=null ) {
					valid.setText(valid.getText()+" Username in use");
				}
				if( u2 !=null ) {
					valid.setText(valid.getText()+" Email in use");
				}
				if(u1==null && u2== null) {
					dm.createUser(user);
					UserStock userStock = new UserStock(user.getId()); 
					UserStockDbMethods usdm = new UserStockDbMethods();
					usdm.saveStockUser(userStock);
					
					App.stage.setScene(new SceneSetter().setLoginScene());
				}
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		
		
	}

}
