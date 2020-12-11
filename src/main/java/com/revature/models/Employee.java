package com.revature.models;

public class Employee extends User {
	
	private String employeeName;

	public Employee(String employeeID, int password, String name) {
		super(employeeID, password);
		this.employeeName = name;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
}
