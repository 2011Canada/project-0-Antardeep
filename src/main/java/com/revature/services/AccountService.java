package com.revature.services;

import java.util.List;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;

public interface AccountService {
	
	public void newAccountService(String acType, double acBalance) throws NegativeBalanceException, AccountNotFoundException, UserNotFoundException;
	
	public List<Account>  getAccountsByCustomerID();
	
	public void getAccountByAccountNo(int accountNo) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException;
	
	public List<Account> getAccountsByPendingStatus();
	
	public void approveRejectService(int accountNo) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException;

}
