package com.revature.repositories;


import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;


public interface BankDAO {
	
	public void registerEmployee(Employee e);
	
	public void registerCustomer(Customer c);
	
	public User login(String type, String userID, int password) throws UserNotFoundException;

}
