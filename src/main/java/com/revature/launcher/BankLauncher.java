package com.revature.launcher;

import com.revature.exceptions.UserNotFoundException;
import com.revature.menu.LoginMenu;
import com.revature.menus.EntertainmentMediaMenu;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.BankDAO;
import com.revature.repositories.BankMemoryDAO;
import com.revature.repositories.EntertainmentMediaDAO;
import com.revature.repositories.EntertainmentMediaMemoryDao;
import com.revature.services.BankService;
import com.revature.services.BankServiceImplementation;
import com.revature.services.EntertainmentMediaService;
import com.revature.services.EntertainmentMediaServiceImplementation;

public class BankLauncher {
	
	public static void main(String[] args) throws UserNotFoundException {
		
		BankDAO bDAO = new BankMemoryDAO();
		BankService bs = new BankServiceImplementation(bDAO);
		LoginMenu lm = new LoginMenu(bs);
		
		while(true) {
			lm.loginDisplay();
		}
		
		
		
	}

}
