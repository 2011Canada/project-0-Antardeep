package com.revature.services;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Transfers;

public interface TransactionService {
	
	public void depositService(int accountNo, double amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException;
	
	public void withdrawService(int accountNo, double amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException;
	
	public void postMoneyService(int accountNo, int accountNoTo, double amount) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException;
	
	public void recieveMoneyService() throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException;
	
	public List<Transfers> getPostedMoneyService();
	
	public List<Transfers> getAllTransactionService();
}
