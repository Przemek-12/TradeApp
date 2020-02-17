package org.openjfx.mavenfx.tableStrategy;

import org.openjfx.mavenfx.trades.Trade;

import javafx.scene.control.TableView;

public abstract class MainTable<T> extends TableView<T> {
	
	public abstract void createTable();
	public abstract void setList(String company);

}
