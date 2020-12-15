package com.revature.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankLauncher;
import com.revature.menu.AccountsMenu;
import com.revature.menu.CustomerMenu;
import com.revature.menu.EmployeeMenu;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountPostgreDAO;

public class AccountServiceImplementation implements AccountService{
	
	private AccountDAO aDAO = new AccountPostgreDAO();
	Scanner sc;
	Map<String, String> accountInfo = new HashMap<String, String>();
	
	public AccountServiceImplementation() {
		this.sc = new Scanner(System.in);
	}

	@Override
	public void newAccountService(String acType, double acBalance) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		aDAO.createAccount(acType, acBalance, (Customer)BankLauncher.getActiveUser());
		System.out.println("\n~~~~ your account is waiting to be approved by employee ~~~\n");
		CustomerMenu cm = new CustomerMenu();
		cm.customerHomeScreenDisplay((Customer)BankLauncher.getActiveUser());
	}

	@Override
	public List<Account> getAccountsByCustomerID() {
		return aDAO.getAccountsByID((Customer)BankLauncher.getActiveUser());
	}

	@Override
	public void getAccountByAccountNo(int accountNo) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		accountInfo = aDAO.getAccountByNo(accountNo);
		System.out.println("_________  Account Info and Cutomer __________");
		System.out.println("ACCOUNT TYPE: " + accountInfo.get("acType") + "\nACCOUNT NUMBER: " + accountInfo.get("acNumber") 
						+ "\nACCOUNT BALANCE: " + accountInfo.get("acBalance") + "\nSTATUS: " + accountInfo.get("acStatus")
						+ "\nCUSTOMER ID: " + accountInfo.get("customerID") + "\nCUSTOMER NAME: " + accountInfo.get("customerName"));
		System.out.println("\nPRESS 1: View more account\t 2: Employee home page");
		this.choice();
	}
	
	private void choice() throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		String input = this.sc.nextLine();
		switch(input) {
		case "1":
			AccountsMenu am = new AccountsMenu();
			am.viewOneAccount();
			break;
		case "2":
			EmployeeMenu em = new EmployeeMenu();
			em.employeeHomeScreenDisplay((Employee)BankLauncher.getActiveUser());
			break;
		default:
			System.out.println("Please press valid key");
			this.choice();
			break;
		}
	}

	@Override
	public List<Account> getAccountsByPendingStatus() {
		return aDAO.getAccountByStatus();
	}

	@Override
	public void approveRejectService(int accountNo) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		System.out.println("Enter 'Y' to Approve or 'N' to Reject");
		this.choice1(accountNo);
	}
	
	private void choice1(int accountNo) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		AccountsMenu am = new AccountsMenu();
		switch(this.sc.nextLine()) {
		case "Y":
			aDAO.approveReject(accountNo, "Approved");
			am.viewNewAccountRequest();
			break;
		case "N":
			aDAO.approveReject(accountNo, "Rejected");
			am.viewNewAccountRequest();
		default:
			System.out.println("Please press valid key");
			this.choice1(accountNo);
			break;			
		}
	}

}
