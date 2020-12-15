package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.models.Customer;
import com.revature.models.Transfers;

public interface TransactionDAO {
	
	public void deposit(int accountNo, double amount) throws AccountNotFoundException;
	
	public void withdraw(int accountNo, double amount) throws NegativeBalanceException, AccountNotFoundException;
	
	public void postMoney(int accountNo, int accountNoTo, double amount) throws NegativeBalanceException, AccountNotFoundException;
	
	public void recieveMoney() throws AccountNotFoundException;
	
	public List<Transfers> getPostedMoney(Customer c);
	
	public List<Transfers> getAllTransactions();

}
