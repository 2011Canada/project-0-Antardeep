package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;


public interface BankDAO {
	//read all
	public List<User> findAll();
	
	//read one
	public void findUserByID(String userID, int password) throws UserNotFoundException;

}
