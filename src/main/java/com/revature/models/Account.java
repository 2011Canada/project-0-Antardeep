package com.revature.models;

public class Account {
	private String accountType;
	private int accountNumber;
	private double accountBalance;
	private String status;
	
	public Account(String accountType, int accountNumber, double accountBalance, String status) {
		super();
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.status = status;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", accountNumber=" + accountNumber + ", accountBalance="
				+ accountBalance + ", status=" + status + "]";
	}
	
	

}
