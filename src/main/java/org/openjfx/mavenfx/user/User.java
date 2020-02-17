package org.openjfx.mavenfx.user;

import javax.persistence.*;

import org.openjfx.mavenfx.trades.Trade;

import java.io.Serializable;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idUsers")
	private int Id;
	
	@Column(name="userName")
	private String UserName;
	
	@Column(name="password")
	private String Password;
	
	@Column(name="email")
	private String Email;
	
	
	public User(){
		
	}
	
	public User( String UserName, String Password, String Email) {
		this.UserName= UserName;
		this.Email=Email;
		this.Password = Password;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}
	
	@Override
	public String toString() {
		return this.Password+this.UserName+this.Email;
	}
	
	@Override
	public boolean equals(Object u) {
		
		if(this.getId()==((User) u).getId() ){
			return true;
		}
		
		return false;
	}
	
}
