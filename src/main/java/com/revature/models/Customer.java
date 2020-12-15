package com.revature.models;

public class Customer extends User{

	private String cusFirstName;
	private String cusLastName;
	private String cusPhone;
	private String cusEmail;
	private String cusAddress;
	private int cusID;

	public Customer(String userID, int password, String fName, String lName, String phone, String email, String Address) {
		super(userID, password, "Customer");
		this.cusFirstName = fName;
		this.cusLastName = lName;
		this.cusPhone = phone;
		this.cusEmail = email;
		this.cusAddress = Address;
	}
	
	public Customer() {
		super();
		this.setAccountType("Customer");
	}

	public String getCusFirstName() {
		return cusFirstName;
	}

	public void setCusFirstName(String cusFirstName) {
		this.cusFirstName = cusFirstName;
	}

	public String getCusLastName() {
		return cusLastName;
	}

	public void setCusLastName(String cusLastName) {
		this.cusLastName = cusLastName;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public int getCusID() {
		return cusID;
	}

	public void setCusID(int cusID) {
		this.cusID = cusID;
	}

	@Override
	public String toString() {
		return "[" + super.toString() + "Customer [cusFirstName=" + cusFirstName + ", cusLastName=" + cusLastName + ", cusPhone=" + cusPhone
				+ ", cusEmail=" + cusEmail + ", cusAddress=" + cusAddress + ", cusID=" + cusID +"]"+ "]";
	}

	

}
