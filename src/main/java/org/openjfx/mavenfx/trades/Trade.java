package org.openjfx.mavenfx.trades;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name="trades")
public class Trade {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="tradeId")
	private int tradeId;
	
	@Column(name="company")
	private String company;
	
	@Column(name="ordertime")
	private LocalDateTime orderTime;
	
	@Column(name="buyOrSell")
	private String buyOrSell;
	
	@Column(name="idUsers")
	private int idUsers;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="totalPrice")
	private Double totalPrice;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="tradeStatus")
	private String tradeStatus;
	
	@Column(name="amountToTrade")
	private int amountToTrade;
	
	
	public Trade() {}

	public Trade(String company, LocalDateTime orderTime, String buyOrSell, int idUsers, Double price,
			Double totalPrice, int amount, String tradeStatus, int amountToTrade) {
		this.company = company;
		this.orderTime = orderTime;
		this.buyOrSell = buyOrSell;
		this.idUsers = idUsers;
		this.price = price;
		this.totalPrice = totalPrice;
		this.amount = amount;
		this.tradeStatus = tradeStatus;
		this.amountToTrade=amountToTrade;
	}



	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}
	public String getBuyOrSell() {
		return buyOrSell;
	}
	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	public int getIdUsers() {
		return idUsers;
	}
	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	public int getAmountToTrade() {
		return amountToTrade;
	}
	public void setAmountToTrade(int amountToTrade) {
		this.amountToTrade=amountToTrade;
	}
	
	
	
	@Override
	public boolean equals(Object t) {
		
		if(this.getTradeId()==((Trade) t).getTradeId() ){
			return true;
		}
		
		return false;
	}
	
}
