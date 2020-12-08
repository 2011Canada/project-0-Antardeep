package com.revature.models;

public abstract class User {
	
	//fields
	private String userName;
	private int password;
	
	//constructor
	public User(String userName, int password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	
	//getter and setter
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}
	
}
