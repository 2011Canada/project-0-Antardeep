package com.revature.menu;

import com.revature.models.Customer;
import com.revature.models.User;

public class CustomerMenu {
	
	public void CustomerHomeScreenDisplay(Customer c) {
		System.out.println("_____________Customer______________");
		System.out.println("Employee ID: "+c.getUserID());
		System.out.println("Employee Name: "+c.getCustomerName());
		System.out.println("___________________________________");
	}

}
