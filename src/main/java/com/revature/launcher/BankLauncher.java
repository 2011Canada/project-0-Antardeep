package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.menu.LoginMenu;
import com.revature.models.User;
import com.revature.repositories.BankDAO;
import com.revature.repositories.BankPostgreDAO;
//import com.revature.repositories.BankMemoryDAO;
import com.revature.services.BankService;
import com.revature.services.BankServiceImplementation;


public class BankLauncher {
	
	public static Logger bankLogger = LogManager.getLogger("com.revature.bank");
	
	private static User activeUser;
	
	public static User getActiveUser() {
		return activeUser;
	}

	public static void setActiveUser(User activeUser) {
		BankLauncher.activeUser = activeUser;
	}


	public static void main(String[] args) throws UserNotFoundException, NegativeBalanceException, AccountNotFoundException {
		
		bankLogger.info("Server Started");
		
		BankDAO bDAO = new BankPostgreDAO();
		BankService bs = new BankServiceImplementation(bDAO);
		LoginMenu lm = new LoginMenu(bs);
		
		while(true) {
			lm.chooseType();
		}
		
	}

}
