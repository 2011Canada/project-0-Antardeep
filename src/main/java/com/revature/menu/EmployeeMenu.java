package com.revature.menu;

import com.revature.models.Employee;
import com.revature.models.User;

public class EmployeeMenu {

	public void employeeHomeScreenDisplay(Employee e) {
		System.out.println("_____________Employee______________");
		System.out.println("Employee ID: "+e.getUserID());
		System.out.println("Employee Name: "+e.getEmployeeName());
		System.out.println("___________________________________");
	}
}
