package com.revature.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankLauncher;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.services.AccountService;
import com.revature.services.AccountServiceImplementation;

public class AccountsMenu {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m"; 
	public static final String ANSI_GREEN = "\u001B[32m"; 
	
	private AccountService ac = new AccountServiceImplementation();
	Scanner sc;
	TransactionMenu tm = new TransactionMenu();
	List<Account> listOfAccounts=new ArrayList<Account>();
	
	public AccountsMenu() {
		this.sc = new Scanner(System.in);
	}
	
	public void newAccount() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		System.out.println("_________  Account Info  __________");
		System.out.print("Account Type:  ");
		String acType = this.sc.nextLine();
		
		double acBalance = this.accountBalance();
		
		ac.newAccountService(acType, acBalance);
	}
	
	private double accountBalance() {
		System.out.print("Starting Account Balance (must be numeric value greater than 0):  ");
		double temp = (double)this.sc.nextDouble();
		if(temp < 0 || temp == 0) {
			System.out.println("Starting balance can't be 0 or negative");
			this.accountBalance();
		}
		return temp;
	}
	
	public void viewAccounts() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		listOfAccounts = ac.getAccountsByCustomerID();
		int currentPosition = 1;
		System.out.println("\n-----------------------------------YOUR ACCOUNTS-------------------------------------");
		for(Account accounts : listOfAccounts) {
				if(accounts.getStatus().equals("Rejected")) {
					System.out.println("\n\n" +currentPosition + "\tTYPE: " + accounts.getAccountType() + "\tACCOUNT BALANCE: " + accounts.getAccountBalance() 
						+ "\t" + ANSI_RED + accounts.getStatus() + ANSI_RESET);
				}else if(accounts.getStatus().equals("Approved")) {
					System.out.println("\n" + currentPosition  + "\tTYPE: " + accounts.getAccountType() + "\tACCOUNT NUMBER: " + accounts.getAccountNumber()  
							+ "\tACCOUNT BALANCE: " + accounts.getAccountBalance() +  "\t" + ANSI_GREEN + accounts.getStatus() + ANSI_RESET);
				}else if(accounts.getStatus().equals("Pending")){
					System.out.println("\n" + currentPosition +  "\tTYPE: " + accounts.getAccountType() + "\tACCOUNT BALANCE: " + accounts.getAccountBalance()
							+ "\tSTATUS: " + accounts.getStatus());
				}
			 	currentPosition++;
		}
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("\nchoose a transaction you want to perform\n");
		
		System.out.println("\n1 : DEPOSIT\t 2 : WITHDRAW\t 3: POST MONEY\t 4 : RECIEVE MONEY\t 5 : PREVIOUS MENU ");
		this.choice();
	}
	
	private void choice() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		switch(this.sc.nextLine()) {
		case "1":
			tm.depositDisplay();
			break;
		case "2":
			tm.withdrawDisplay();
			break;
		case "3":
			tm.postMoneyDisplay();
			break;
		case "4":
			tm.recieveMoneyDisplay();
			break;
		case "5":
			CustomerMenu cm = new CustomerMenu();
			cm.customerHomeScreenDisplay((Customer)BankLauncher.getActiveUser());
		default:
			System.out.println("Please press valid key");
			break;			
		}
	}
	
	public void viewOneAccount() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		System.out.println("\nEnter account number: ");
		int accountNo = this.sc.nextInt();
		ac.getAccountByAccountNo(accountNo);
	}
	
	public void viewNewAccountRequest() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		listOfAccounts = ac.getAccountsByPendingStatus();
		int currentPosition = 1;
		System.out.println("\n------------------------New Account Requests----------------------------");
		for(Account accounts : listOfAccounts) {
			System.out.println("\n" +currentPosition + "\tTYPE: " + accounts.getAccountType() + "\tACCOUNT NUMBER: " + accounts.getAccountNumber() 
						+ "\tSTATUS: " + accounts.getStatus());
			 	currentPosition++;
		}
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("\n1 : SELECT AN ACCOUNT\t 2 : EMPLOYEE HOME PAGE ");
		this.choice1();
	}
	
	private void choice1() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		switch(this.sc.nextLine()) {
		case "1":
			this.accountDetail();
			break;
		case "2":
			EmployeeMenu em = new EmployeeMenu();
			em.employeeHomeScreenDisplay((Employee)BankLauncher.getActiveUser());
		default:
			System.out.println("Please press valid key");
			break;			
		}
	}
	
	private void accountDetail() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		System.out.println("Enter account number");
		int accountNo = this.sc.nextInt();
		ac.approveRejectService(accountNo);
	}
	
	
}
