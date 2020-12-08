package com.revature.models;

public class Employee extends User {
	
	private String employeeID;

	public Employee(String userName, int password, String employeeID) {
		super(userName, password);
		this.employeeID = employeeID;
	}
	
}
