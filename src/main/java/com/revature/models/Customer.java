package com.revature.models;

public class Customer extends User{

	private String customerName;

	public Customer(String customerID, int password, String name) {
		super(customerID, password);
		this.customerName = name;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	

}
