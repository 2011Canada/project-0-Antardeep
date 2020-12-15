package com.revature.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankLauncher;
import com.revature.models.Employee;
import com.revature.models.Transfers;
import com.revature.services.TransactionService;
import com.revature.services.TransactionServiceImplementation;

public class TransactionMenu {
	
	TransactionService ts = new TransactionServiceImplementation();
	
	Scanner sc;
	int accountNo, accountNoTo;
	double amount;
	
	List<Transfers> listOfTransfers = new ArrayList<Transfers>();
	
	public TransactionMenu() {
		this.sc = new Scanner(System.in);
	}
	
	public void depositDisplay() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		System.out.println("Account Number: ");
		accountNo = this.sc.nextInt();
		amount = this.amount();
		ts.depositService(accountNo, amount);
	}
	
	public void withdrawDisplay() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		System.out.println("Account Number: ");
		accountNo = this.sc.nextInt();
		amount = this.amount();
		ts.withdrawService(accountNo, amount);
	}
	
	public void postMoneyDisplay() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		System.out.println("From Account Number: ");
		accountNo = this.sc.nextInt();
		System.out.println("To Account Number: ");
		accountNoTo = this.sc.nextInt();
		amount = this.amount();
		ts.postMoneyService(accountNo, accountNoTo, amount);
	}

	private double amount() {
		System.out.println("Amount: ");
		double temp = (double)this.sc.nextDouble();
		if(temp < 0 || temp == 0) {
			System.out.println("Amount can't be 0 or negative");
			this.amount();
		}
		return temp;
	}
	
	public void recieveMoneyDisplay() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		listOfTransfers = ts.getPostedMoneyService();
		int currentPosition = 1;
		System.out.println("--------------------------------------------------------------");
		if(listOfTransfers.isEmpty()) {
			System.out.println("~~~~ you don't have any money to recieve ~~~~");
		}
		for(Transfers trans : listOfTransfers) {
				System.out.println("\n" +currentPosition + "\tAMOUNT: " + trans.getTransfeAmount() + "\tFROM ACCOUNT: " + trans.getFromAccount() 
				+ "\tTO ACCOUNT: " + trans.getToAccount());
			 	currentPosition++;
		}
		System.out.println("--------------------------------------------------------------");
		System.out.println("\nPress Y to accept money or N to go back\n");
		this.choice();
	}

	private void choice() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		switch(this.sc.nextLine()) {
		case "Y":
			ts.recieveMoneyService();
			break;
		case "N":
			AccountsMenu am = new AccountsMenu();
			am.viewAccounts();
			break;
		default:
			System.out.println("Please press valid key");
			this.choice();
			break;			
		}
	}
	
	public void viewAllTransactions() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		listOfTransfers = ts.getAllTransactionService();
		int currentPosition = 1;
		System.out.println("-----------------------------------------Log of all Transactions------------------------------------------");
		if(listOfTransfers.isEmpty()) {
			System.out.println("~~~~ No Transaction Performed yet ~~~~");
		}
		for(Transfers trans : listOfTransfers) {
				System.out.println("\n" +currentPosition + "\tTRANSFER AMOUNT: " + trans.getTransfeAmount() + "\t\tFROM ACCOUNT: " + trans.getFromAccount() 
				+ "\t\tTO ACCOUNT: " + trans.getToAccount() + "\t\tSTATUS: " + trans.getStatus());
			 	currentPosition++;
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println("\nPress E to Exit\n");
		this.choice1();
	}
	
	private void choice1() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		switch(this.sc.nextLine()) {
		case "E":
			EmployeeMenu em = new EmployeeMenu();
			em.employeeHomeScreenDisplay((Employee)BankLauncher.getActiveUser());
			break;
		default:
			System.out.println("Please press valid key");
			this.choice1();
			break;			
		}
	}
	
	
	

}
