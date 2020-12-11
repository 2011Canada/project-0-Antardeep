package com.revature.services;

import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;


public interface BankService {
	
	public List<User> seeAllUsers();
	
	public void loginService(String ID, int password) throws UserNotFoundException;

}
