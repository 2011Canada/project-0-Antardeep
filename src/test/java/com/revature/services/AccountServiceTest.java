package com.revature.services;




import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.repositories.AccountDAO;
import com.revature.repositories.AccountPostgreDAO;


public class AccountServiceTest {

	private AccountDAO a;
	
	@BeforeEach
	public void setupAccountTest() {
		this.a = new AccountPostgreDAO();
	}
	
	@Test
	public void testGetAccountByNo() {
		Map<String, String> accountInfo = new HashMap<String, String>();
		  accountInfo.put("acType", "saving");
		  accountInfo.put("acNumber", Integer.toString(3));
		  accountInfo.put("acBalance", Double.toString(100.99));
		  accountInfo.put("acStatus", "Approved");
		  accountInfo.put("customerID", Integer.toString(1));
		  accountInfo.put("customerName", "cus 1");
		  
		  assertEquals(accountInfo, a.getAccountByNo(3));
	}
	
}
