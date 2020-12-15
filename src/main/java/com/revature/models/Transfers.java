package com.revature.models;

public class Transfers {

	String status;
	double transfeAmount;
	int fromAccount;
	int toAccount;
	
	public Transfers(String status, double transfeAmount, int fromAccount, int toAccount) {
		super();
		this.status = status;
		this.transfeAmount = transfeAmount;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTransfeAmount() {
		return transfeAmount;
	}

	public void setTransfeAmount(double transfeAmount) {
		this.transfeAmount = transfeAmount;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	@Override
	public String toString() {
		return "Transfers [status=" + status + ", transfeAmount=" + transfeAmount + ", fromAccount=" + fromAccount
				+ ", toAccount=" + toAccount + "]";
	}
	
}
