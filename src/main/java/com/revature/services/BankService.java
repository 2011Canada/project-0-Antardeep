package com.revature.services;



import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;



public interface BankService {
	
//	public List<User> seeAllUsers();
	
	public void loginService(String type, String ID, int password) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException;
	
	public void registerUser(String type, String userID, int password, String fname, String lname, String phone, String email, String address);

}
