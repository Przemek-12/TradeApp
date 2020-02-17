package org.openjfx.mavenfx.tableStrategy;

import org.openjfx.mavenfx.panes.ListsPane;


public class TableDataSetter {

	public static String company;
	
	public void setCompany(String company) {
		this.company=company;
	}
	
	public void setTableData() {
		ListsPane lp = new ListsPane();
		lp.getSubmitedTable().setList(company);
		lp.getCompletedTable().setList(company);
		lp.getBuyTable().setList(company);
		lp.getSellTable().setList(company);
		
	}
}
