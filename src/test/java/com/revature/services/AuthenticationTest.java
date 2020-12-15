package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.UserNotFoundException;
import com.revature.repositories.BankDAO;
import com.revature.repositories.BankPostgreDAO;

public class AuthenticationTest {
	
	private BankDAO b;
	
	@BeforeEach
	public void setupLoginTest() {
		this.b = new BankPostgreDAO();
	}
	
	@Test
	public void testLogin() throws UserNotFoundException {
		assertNotNull(b.login("Customer", "customer1", 123));
	}

}
