package com.revature.models;

public abstract class User {
	
	//fields
	private String userID;
	private int password;
	
	//constructor
	public User(String userID, int password) {
		super();
		this.userID = userID;
		this.password = password;
	}
	public User() {
		super();
	}

	
	//getter and setter
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}
	
}
