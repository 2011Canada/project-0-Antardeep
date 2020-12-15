package com.revature.menu;

import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankLauncher;
import com.revature.models.Customer;


public class CustomerMenu {
	
	Scanner sc;
	AccountsMenu am = new AccountsMenu();
	
	public CustomerMenu() {
		this.sc = new Scanner(System.in);
	}
	
	public void customerHomeScreenDisplay(Customer c) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		System.out.println("\n_______________________   CUSTOMER HOME PAGE   _________________________");
		System.out.println("\nCustomer Personal Info : ");
		System.out.println("\t\t\tName: "+ c.getCusFirstName() + " " + c.getCusLastName() + "\n\t\t\tPhone Number: " + c.getCusPhone() + 
				"\n\t\t\tEmail: " + c.getCusEmail() + "\n\t\t\tAddress: " + c.getCusAddress());
		
		System.out.print("\nPRESS 1 : APPLY FOR NEW ACCOUNT\t 2: VIEW ACCOUNTS\t 3: LOGOUT\n\n");
		this.choice();
		
		System.out.println("___________________________________________________________________________");
	}
	
	private void choice() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		switch(this.sc.nextLine()) {
		case "1":
			am.newAccount();
			break;
		case "2":
			am.viewAccounts();
			break;
		case "3":
			BankLauncher.bankLogger.info("Customer Logged out");
			BankLauncher.main(null);
			break;
		default:
			System.out.println("Please press valid key");
			this.choice();
			break;			
		}
	}
	
	
	

}
