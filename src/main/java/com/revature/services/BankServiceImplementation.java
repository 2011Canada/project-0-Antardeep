package com.revature.services;

import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.BankDAO;

public class BankServiceImplementation implements BankService{
	
	private BankDAO bDAO;
	
	public BankServiceImplementation(BankDAO bDAO) {
		this.bDAO = bDAO;
	}
	
	public List<User> seeAllUsers() {
		
		return bDAO.findAll();
	}
	
	public void loginService(String userID, int password) throws UserNotFoundException {
		bDAO.findUserByID(userID, password);
	}

}
