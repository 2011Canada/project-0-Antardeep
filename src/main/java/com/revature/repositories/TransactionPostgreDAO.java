package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.exceptions.AccountNotFoundException;
import com.revature.exceptions.NegativeBalanceException;
import com.revature.launcher.BankLauncher;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.Transfers;
import com.revature.util.ConnectionFactory;

public class TransactionPostgreDAO implements TransactionDAO{
	
	private AccountDAO aDAO = new AccountPostgreDAO();
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override
	public void deposit(int accountNo, double amount) throws AccountNotFoundException {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String depositSQL = "update \"accounts\" set \"account_balance\" = \"account_balance\"+? WHERE account_no = ? and account_status = ?;";
			
			PreparedStatement insertDeposit = conn.prepareStatement(depositSQL);
			insertDeposit.setDouble(1, amount);
			insertDeposit.setInt(2, accountNo);
			insertDeposit.setString(3, "Approved");
			
			int r = insertDeposit.executeUpdate();	
			
			if(r == 0) {
				throw new AccountNotFoundException();
			}
			System.out.println("~~~~~~Deposit is successful~~~~~~~~");
			BankLauncher.bankLogger.info("Deposit : Amount " + amount + " to Account No. " + accountNo);
			
			
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
	public void withdraw(int accountNo, double amount) throws NegativeBalanceException, AccountNotFoundException {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String getBalanceSQL = "select \"account_balance\" from \"accounts\" where \"account_no\" = ? and \"account_status\" = ?;";
			PreparedStatement insertaccountNo = conn.prepareStatement(getBalanceSQL);
			insertaccountNo.setInt(1, accountNo);
			insertaccountNo.setString(2, "Approved");
			
			ResultSet rs = insertaccountNo.executeQuery();
			
			double balance;
			if (rs.next()) {
				balance = rs.getInt("account_balance");
			} else {
				throw new AccountNotFoundException();
			}
			
			if((balance - amount) < 0) {
				throw new NegativeBalanceException();
			}else {
				String withdrawSQL = "update \"accounts\" set \"account_balance\" = \"account_balance\"-? WHERE account_no = ?;";
				
				PreparedStatement insertWithdraw = conn.prepareStatement(withdrawSQL);
				insertWithdraw.setDouble(1, amount);
				insertWithdraw.setInt(2, accountNo);
				
				insertWithdraw.executeUpdate();	
				
				System.out.println("~~~~~~Withdraw is successful~~~~~~~~");
				BankLauncher.bankLogger.info("Withdraw : Amount " + amount + " from Account No." + accountNo);
				
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
	public void postMoney(int accountNo, int accountNoTo, double amount) throws NegativeBalanceException, AccountNotFoundException {
		Connection conn = cf.getConnection();
		try {
			
			Map<String, String> accountInfo = new HashMap<String, String>();
			accountInfo = aDAO.getAccountByNo(accountNoTo);
		
			if(!accountInfo.get("acStatus").equals("Approved")) {
				System.out.println("going here<<<<<<");
				throw new AccountNotFoundException();
			}
			
			this.withdraw(accountNo, amount);
			
			conn.setAutoCommit(false);

			String postSQL = "insert into \"transfers\" (\"transfer_amount\", \"status\", \"from_account\", \"to_account\") values (?, ?, ? ,?);";
			
			PreparedStatement insertPost = conn.prepareStatement(postSQL);
			insertPost.setDouble(1, amount);
			insertPost.setString(2, "Posted");
			insertPost.setInt(3, accountNo);
			insertPost.setInt(4, accountNoTo);
			
			insertPost.executeUpdate();	
			System.out.println("~~~~~~Post Money tranfer Successful~~~~~~~~");
			BankLauncher.bankLogger.info("Money sent from Account No. " + accountNo + " to Account No. " + accountNoTo);
			
		
			
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
	public List<Transfers> getPostedMoney(Customer c) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			String getPostedMoneySQL = "select * from \"transfers\" as t inner join \"accounts\" as a on t.to_account = a.account_no where cus_ref = ? and t.status = ?;";
			PreparedStatement getPostedMoney = conn.prepareStatement(getPostedMoneySQL);
			getPostedMoney.setInt(1, c.getCusID());
			getPostedMoney.setString(2, "Posted");
			
			ResultSet rs = getPostedMoney.executeQuery();
			
			List<Transfers> list=new ArrayList<Transfers>();
			
			while (rs.next()) {
				String status = rs.getString("status");
				double transferAmount = rs.getDouble("transfer_amount");
				int fromAccount = rs.getInt("from_account");
				int toAccount = rs.getInt("to_account");

				Transfers trans = new Transfers(status, transferAmount, fromAccount, toAccount);

				list.add(trans);
			}
			return list;
			
		}catch (SQLException e1) {
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
		return null;
	}

	@Override
	public void recieveMoney() throws AccountNotFoundException {
		Connection conn = cf.getConnection();
		try {
			List<Transfers> listOfTransfers = new ArrayList<Transfers>();
			listOfTransfers = this.getPostedMoney((Customer)BankLauncher.getActiveUser());
		
			for(Transfers trans : listOfTransfers) {
				this.deposit(trans.getToAccount(), trans.getTransfeAmount());
				
				String setTransferStatusSQL = "update \"transfers\" set \"status\" = ? WHERE \"to_account\" = ?;";
				PreparedStatement insertTransferStatus = conn.prepareStatement(setTransferStatusSQL);
				insertTransferStatus.setString(1, "Recieved");
				insertTransferStatus.setInt(2, trans.getToAccount());
				
				insertTransferStatus.executeUpdate();	
			}
			System.out.println("~~~~~~Recieved Money tranfer Successful~~~~~~~~");
			BankLauncher.bankLogger.info("Money Recieved");
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
		}
	}

	@Override
	public List<Transfers> getAllTransactions() {
		Connection conn = cf.getConnection();
		try {
			String getPostedMoneySQL = "select * from \"transfers\"";
			PreparedStatement getPostedMoney = conn.prepareStatement(getPostedMoneySQL);
			
			ResultSet rs = getPostedMoney.executeQuery();
			
			List<Transfers> list=new ArrayList<Transfers>();
			
			while (rs.next()) {
				String status = rs.getString("status");
				double transferAmount = rs.getDouble("transfer_amount");
				int fromAccount = rs.getInt("from_account");
				int toAccount = rs.getInt("to_account");

				Transfers trans = new Transfers(status, transferAmount, fromAccount, toAccount);

				list.add(trans);
			}
			BankLauncher.bankLogger.info("Transactions List Fetched");
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
		}
		return null;
	}

}
