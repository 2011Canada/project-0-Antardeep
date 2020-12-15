package com.revature.exceptions;

public class NegativeBalanceException extends Exception{
	
	public NegativeBalanceException() {
		super("Balance can't be Negative");
	}

}
