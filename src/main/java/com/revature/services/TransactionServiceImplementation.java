package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankLauncher;
import com.revature.menu.AccountsMenu;
import com.revature.menu.CustomerMenu;
import com.revature.models.Customer;
import com.revature.models.Transfers;
import com.revature.repositories.TransactionDAO;
import com.revature.repositories.TransactionPostgreDAO;

public class TransactionServiceImplementation implements TransactionService{
	
	TransactionDAO tDAO = new TransactionPostgreDAO();
	Scanner sc;
	
	public TransactionServiceImplementation() {
		this.sc = new Scanner(System.in);
	}

	@Override
	public void depositService(int accountNo, double amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		
		tDAO.deposit(accountNo, amount);
		System.out.println("PRESS 1 : CUSTOMER HOME SCREEN\t 2: PERFORM MORE TRANSACTION");
		this.choice();
	}

	@Override
	public void withdrawService(int accountNo, double amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		tDAO.withdraw(accountNo, amount);
		System.out.println("PRESS 1 : CUSTOMER HOME SCREEN\t 2: PERFORM MORE TRANSACTION");
		this.choice();
	}

	@Override
	public void postMoneyService(int accountNo, int accountNoTo, double amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		tDAO.postMoney(accountNo, accountNoTo, amount);
		System.out.println("PRESS 1 : CUSTOMER HOME SCREEN\t 2: PERFORM MORE TRANSACTION");
		this.choice();
	}

	@Override
	public void recieveMoneyService() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		tDAO.recieveMoney();
		System.out.println("PRESS 1 : CUSTOMER HOME SCREEN\t 2: PERFORM MORE TRANSACTION");
		this.choice();
	}
	
	private void choice() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException {
		switch(this.sc.nextLine()) {
		case "1":
			CustomerMenu cm = new CustomerMenu();
			cm.customerHomeScreenDisplay((Customer)BankLauncher.getActiveUser());
			break;
		case "2":
			AccountsMenu am = new AccountsMenu();
			am.viewAccounts();
			break;
		default:
			System.out.println("Please press valid key");
			this.choice();
			break;			
		}
	}

	@Override
	public List<Transfers> getPostedMoneyService() {
		return tDAO.getPostedMoney((Customer)BankLauncher.getActiveUser());
	}

	@Override
	public List<Transfers> getAllTransactionService() {
		return tDAO.getAllTransactions();
	}

}
