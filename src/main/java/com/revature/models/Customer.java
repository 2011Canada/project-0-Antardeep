package com.revature.models;

public class Customer extends User{

	private String customerID;

	public Customer(String userName, int password, String customerID) {
		super(userName, password);
		this.customerID = customerID;
	}

}
