package com.revature.services;


import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankLauncher;
import com.revature.menu.CustomerMenu;
import com.revature.menu.EmployeeMenu;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.BankDAO;

public class BankServiceImplementation implements BankService{
	
	private BankDAO bDAO;
	Employee e;
	Customer c;
	EmployeeMenu em = new EmployeeMenu();
	CustomerMenu cm = new CustomerMenu();
	
	public BankServiceImplementation(BankDAO bDAO) {
		this.bDAO = bDAO;
	}
	
	public void loginService(String type, String userID, int password) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
			User u = bDAO.login(type, userID, password);
			BankLauncher.setActiveUser(u);
			if(u instanceof Employee) {
				try {
					BankLauncher.bankLogger.info("Employee Login : " + ((Employee) u).getEmpFirstName() + " " + ((Employee) u).getEmpLastName());
					em.employeeHomeScreenDisplay((Employee)u);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(u instanceof Customer) {
				BankLauncher.bankLogger.info("Customer Login : " + ((Customer) u).getCusFirstName() + " " + ((Customer) u).getCusLastName());
				cm.customerHomeScreenDisplay((Customer)u);
			}
	}
	
	public void registerUser(String type, String userID, int password, String fname, String lname, String phone, String email, String address) {
		if(type.equals("employee")) {
			e = new Employee(userID, password, fname, lname, phone, email, address);
			bDAO.registerEmployee(e);
		}else {
			c = new Customer(userID, password, fname, lname, phone, email, address);
			bDAO.registerCustomer(c);
		}
	}

}
