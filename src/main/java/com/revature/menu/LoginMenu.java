package com.revature.menu;


import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.services.BankService;

public class LoginMenu{
	
	private BankService bs;
	Scanner sc;
	private String type;
	
	public LoginMenu(BankService bs) {
		this.bs = bs;
		this.sc = new Scanner(System.in);
	}
	
	public void mainDisplay() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		System.out.println("PRESS  1 : LOGIN\t 2: REGISTERATION");
		String enteredValue = this.sc.nextLine();
		switch(enteredValue) {
			case "1":
				loginDisplay();
				break;
			case "2":
				registerDisplay();
				break;
			default:
				System.out.println("Please press valid key");
				this.mainDisplay();
				break;
		}
	}
	
	public void chooseType() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		System.out.print("Account type (employee/customer): ");
		type = this.sc.nextLine();
		if(( type.equals("employee") || type.equals("customer") )) {
			this.mainDisplay();
		}else {
			System.out.println("\nPlease choose valid type");
		}
	}
	
	
	public void loginDisplay() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		System.out.println("\n************	USER LOGIN	*************");
		
		System.out.print("User ID:  ");
		String userID = this.sc.nextLine();
		System.out.print("Password:  ");
		int password = Integer.parseInt(this.sc.nextLine());
		
		System.out.println("*********************************************");
		
		bs.loginService(type,userID,password);
	}
	
	public void registerDisplay() {
		System.out.println("\n********	USER REGISTERATION	********");
		
		System.out.println("\nChoose your Login ID and password - (password must be an integer value)");
		System.out.print("User ID:  ");
		String userID = this.sc.nextLine();
		System.out.print("Password:  ");
		int password = Integer.parseInt(this.sc.nextLine());
		
		System.out.println("_________  Additional Information  _____");
		System.out.print("First Name:  ");
		String fname = this.sc.nextLine();
		System.out.print("Last Name:  ");
		String lname = this.sc.nextLine();
		System.out.print("Phone number:  ");
		String phone = this.sc.nextLine();
		System.out.print("Email:  ");
		String email = this.sc.nextLine();
		System.out.print("Address:  ");
		String address = this.sc.nextLine();
		
		System.out.println("****************************************");
		
		bs.registerUser(type, userID, password, fname, lname, phone, email, address);
	}
	
	
	

}
