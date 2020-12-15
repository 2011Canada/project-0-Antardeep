package com.revature.menu;

import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankLauncher;
import com.revature.models.Employee;

public class EmployeeMenu {
	
	Scanner sc;
	AccountsMenu am = new AccountsMenu();
	TransactionMenu tm = new TransactionMenu();
	
	public EmployeeMenu() {
		this.sc = new Scanner(System.in);
	}

	public void employeeHomeScreenDisplay(Employee e) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		System.out.println("\n_____________Employee HOME PAGE______________");
		System.out.println("Employee Personal Info : ");
		System.out.println("\t\t\tName: "+ e.getEmpFirstName() + " " + e.getEmpLastName() + "\n\t\t\tPhone Number: " + e.getEmpPhone() + 
				"\n\t\t\tEmail: " + e.getEmpEmail() + "\n\t\t\tAddress: " + e.getEmpAddress());
		
		System.out.print("\nPRESS 1 : View Specific Customer Bank Account\t 2: View New Account Requests\t 3: View Log of All Transactions\t 4: LOGOUT\n\n");
		this.choice();
		
		System.out.println("_____________________________________________");
	}
	
	private void choice() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		switch(this.sc.nextLine()) {
		case "1":
			am.viewOneAccount();
			break;
		case "2":
			am.viewNewAccountRequest();
			break;
		case "3":
			tm.viewAllTransactions();
			break;
		case "4":
			BankLauncher.bankLogger.info("Customer Logged out");
			BankLauncher.main(null);
			break;
		default:
			System.out.println("Please press valid key");
			break;			
		}
	}
}

