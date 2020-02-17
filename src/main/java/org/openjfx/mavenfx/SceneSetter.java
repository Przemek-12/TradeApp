package org.openjfx.mavenfx;

import org.hibernate.HibernateException;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class SceneSetter {

	public Scene setLoginScene() throws HibernateException, Exception {
		LoginScene loginScene = new LoginScene(new GridPane(),1200,700);
		return loginScene;
	}
	
	public Scene setRegisterScene() {
		RegisterScene registerScene = new RegisterScene(new GridPane(), 1200, 700);
		return registerScene;
	}
	
	public Scene setMenuScene() {
		MenuScene menuScene = new MenuScene(new ScrollPane(),1200,700);
		return menuScene;
	}
	
	public Scene setUserScene() {
		UserScene userScene = new UserScene(new FlowPane(),1200,700);
		return userScene;
	}
	
}
