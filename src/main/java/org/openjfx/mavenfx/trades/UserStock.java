package org.openjfx.mavenfx.trades;

import javax.persistence.*;

@Entity
@Table(name="userstock")
public class UserStock {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="stockId")
	private int stockId;
	
	@Column(name="idUsers")
	private int idUsers;
	
	@Column(name="Gas")
	private int gas;
	
	@Column(name="Gold")
	private int gold;
	
	@Column(name="Microsoft")
	private int microsoft;
	
	@Column(name="Sony")
	private int sony;
	
	public UserStock() {}
	
	//constructor used to create row with empty values for user in userstock table
	public UserStock(int idUsers) {
		this.idUsers=idUsers;
	}
	
	public UserStock(int idUsers, int gas, int gold, int microsoft, int sony) {
		this.idUsers = idUsers;
		this.gas = gas;
		this.gold = gold;
		this.microsoft = microsoft;
		this.sony = sony;
	}
	
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getIdUsers() {
		return idUsers;
	}
	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}
	public int getGas() {
		return gas;
	}
	public void setGas(int gas) {
		this.gas = gas;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getMicrosoft() {
		return microsoft;
	}
	public void setMicrosoft(int microsoft) {
		this.microsoft = microsoft;
	}
	public int getSony() {
		return sony;
	}
	public void setSony(int sony) {
		this.sony = sony;
	}
	
	
}
