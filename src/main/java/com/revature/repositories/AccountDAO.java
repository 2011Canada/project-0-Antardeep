package com.revature.repositories;


import java.util.List;
import java.util.Map;

import com.revature.models.Account;
import com.revature.models.Customer;


public interface AccountDAO {
	
	public void createAccount(String acType, double acBalance, Customer c);
	
	public List<Account> getAccountsByID(Customer c);
	
	public Map<String, String> getAccountByNo(int accountNo);
	
	public List<Account> getAccountByStatus();
	
	public void approveReject(int accountNo, String status);

}
