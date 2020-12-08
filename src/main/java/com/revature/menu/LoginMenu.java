package com.revature.menu;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Displayable;
import com.revature.models.EntertainmentMedia;

public class LoginMenu implements Displayable{
	
	Scanner sc;
	
	public LoginMenu() {
		sc = new Scanner(System.in);
	}

	public void Display() {
		System.out.println("***********USER LOGIN************");
		System.out.println("User name & Password ");
	}
	
	public void manageUserInput() {
		String userName = this.sc.nextLine();
		String password = this.sc.nextLine();
	}
	
	

}
