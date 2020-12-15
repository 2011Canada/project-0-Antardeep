package com.revature.models;

public class Employee extends User {
	
	private String empFirstName;
	private String empLastName;
	private String empPhone;
	private String empEmail;
	private String empAddress;
	private int empID;

	public Employee(String userID, int password, String fName, String lName, String phone, String email, String Address) {
		super(userID, password, "Employee");
		this.empFirstName = fName;
		this.empLastName = lName;
		this.empPhone = phone;
		this.empEmail = email;
		this.empAddress = Address;
	}
	
	public Employee() {
		super();
		this.setAccountType("Employee");
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	@Override
	public String toString() {
		return "[" + super.toString() + "\nEmployee [empFirstName=" + empFirstName + ", empLastName=" + empLastName + ", empPhone=" + empPhone
				+ ", empEmail=" + empEmail + ", empAddress=" + empAddress + ", empID=" + empID + "]"+"]";
	}



	
	
}
