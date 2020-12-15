package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.launcher.BankLauncher;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Transfers;
import com.revature.util.ConnectionFactory;

public class AccountPostgreDAO implements AccountDAO{
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override
	public void createAccount(String acType, double acBalance, Customer c) {
		Connection conn = cf.getConnection();
		try {
			
			conn.setAutoCommit(false);

			String accountSQL = "insert into \"accounts\" (\"account_type\", \"account_balance\", \"account_status\", \"cus_ref\") values (?, ?, ?, ?) returning \"account_no\";";
			
			PreparedStatement insertAccount = conn.prepareStatement(accountSQL);
			insertAccount.setString(1, acType);
			insertAccount.setDouble(2, acBalance);
			insertAccount.setString(3, "Pending");
			insertAccount.setInt(4, c.getCusID());
			
			ResultSet rs = insertAccount.executeQuery();
			
			if (rs.next()) {
				int accNo = rs.getInt("account_no");
				BankLauncher.bankLogger.info("New Account Created : Account No. " + accNo + " by Customer ID " + c.getCusID());
				return;
			} else {
				throw new SQLException();
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		
	}

	@Override
	public List<Account> getAccountsByID(Customer c) {
		Connection conn = cf.getConnection();
		try {
			String getAccountsSQL = "select * from \"accounts\" where \"cus_ref\" = ?;";
			PreparedStatement getAccounts = conn.prepareStatement(getAccountsSQL);
			getAccounts.setInt(1, c.getCusID());
			
			ResultSet rs = getAccounts.executeQuery();
			
			List<Account> list=new ArrayList<Account>();
			
			while (rs.next()) {
				  String acType = rs.getString("account_type");
				  int acNumber = rs.getInt("account_no");
				  double acBalance = rs.getDouble("account_balance");
				  String acStatus = rs.getString("account_status");

				  Account ac = new Account(acType, acNumber, acBalance, acStatus);

				  list.add(ac);
				}
			BankLauncher.bankLogger.info("Customer Accounts Fetched");
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
		}
		return null;
	}

	@Override
	public Map<String, String> getAccountByNo(int accountNo) {
		Connection conn = cf.getConnection();
		try {
			String getAccountSQL = "select * from \"accounts\" inner join \"customer\" on \"cus_ref\" = \"customer_id\" where \"account_no\" = ?;";
			PreparedStatement getAccount = conn.prepareStatement(getAccountSQL);
			getAccount.setInt(1, accountNo);
			
			ResultSet rs = getAccount.executeQuery();
				
			Map<String, String> accountInfo = new HashMap<String, String>();
			while (rs.next()) {
				  String acType = rs.getString("account_type");
				  int acNumber = rs.getInt("account_no");
				  double acBalance = rs.getDouble("account_balance");
				  String acStatus = rs.getString("account_status");
				  
				  int customerID = rs.getInt("customer_id");
				  String customerName = rs.getString("first_name") + " " + rs.getString("last_name");
				  
				  accountInfo.put("acType", acType);
				  accountInfo.put("acNumber", Integer.toString(acNumber));
				  accountInfo.put("acBalance", Double.toString(acBalance));
				  accountInfo.put("acStatus", acStatus);
				  accountInfo.put("customerID", Integer.toString(customerID));
				  accountInfo.put("customerName", customerName);

			}
			BankLauncher.bankLogger.info("Account Fetched : Account Number - " + accountInfo.get("acNumber"));
			return accountInfo;
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
		}
		return null;
	}

	@Override
	public List<Account> getAccountByStatus() {
		Connection conn = cf.getConnection();
		try {
			String getAccountsSQL = "select * from \"accounts\" where \"account_status\" = ?;";
			PreparedStatement getAccounts = conn.prepareStatement(getAccountsSQL);
			getAccounts.setString(1, "Pending");
			
			ResultSet rs = getAccounts.executeQuery();
			
			List<Account> list=new ArrayList<Account>();
			
			while (rs.next()) {
				  String acType = rs.getString("account_type");
				  int acNumber = rs.getInt("account_no");
				  double acBalance = rs.getDouble("account_balance");
				  String acStatus = rs.getString("account_status");

				  Account ac = new Account(acType, acNumber, acBalance, acStatus);

				  list.add(ac);
				}
			BankLauncher.bankLogger.info("New Accounts Request List Fetched");
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
		}
		return null;
	}

	@Override
	public void approveReject(int accountNo, String status) {
		Connection conn = cf.getConnection();
		try {	
			String setAccountStatusSQL = "update \"accounts\" set \"account_status\" = ? WHERE \"account_no\" = ?;";
			PreparedStatement insertAccountStatus = conn.prepareStatement(setAccountStatusSQL);
			insertAccountStatus.setString(1, status);
			insertAccountStatus.setInt(2, accountNo);
				
			insertAccountStatus.executeUpdate();	
	
			System.out.println("~~~~~~~Account No. " + accountNo + " : " + status + "~~~~~~~");
			BankLauncher.bankLogger.info("Account " + status + " :  Account Number - " + accountNo);
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
		}
	}

}
