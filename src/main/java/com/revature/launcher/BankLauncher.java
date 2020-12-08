package com.revature.launcher;

import com.revature.menu.LoginMenu;
import com.revature.models.Customer;
import com.revature.models.Employee;

public class BankLauncher {
	
	public static void main(String[] args) {
		
		LoginMenu lm = new LoginMenu();
		
		while(true) {
			lm.Display();
			lm.manageUserInput();
		}
		
		
		
	}

}
