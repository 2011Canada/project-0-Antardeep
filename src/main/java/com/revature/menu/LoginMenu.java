package com.revature.menu;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Displayable;
import com.revature.models.EntertainmentMedia;
import com.revature.services.BankService;
import com.revature.services.BankServiceImplementation;

public class LoginMenu{
	
	BankService bs;
	
	Scanner sc;
	
	public LoginMenu(BankService bs) {
		this.bs = bs;
		sc = new Scanner(System.in);
	}
	

	public void loginDisplay() throws UserNotFoundException {
		System.out.println("************USER LOGIN*************");
		System.out.print("User ID:  ");
		String userID = this.sc.nextLine();
		System.out.print("Password:  ");
		int password = Integer.parseInt(this.sc.nextLine());
		System.out.println("***********************************");
		bs.loginService(userID,password);
	}
	

}
