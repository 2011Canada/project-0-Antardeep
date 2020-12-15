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
		System.out.println("\n_____________CUSTOMER HOME PAGE______________");
		System.out.println("Customer Personal Info : ");
		System.out.println("\t\t\tName: "+ c.getCusFirstName() + " " + c.getCusLastName() + "\n\t\t\tPhone Number: " + c.getCusPhone() + 
				"\n\t\t\tEmail: " + c.getCusEmail() + "\n\t\t\tAddress: " + c.getCusAddress());
		
		System.out.print("\nPRESS 1 : Apply for new Account\t 2: View Accounts\t 3: LogOut\n\n");
		this.choice();
		
		System.out.println("_____________________________________________");
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
			break;			
		}
	}
	
	
	

}
