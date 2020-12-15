package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankLauncher;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class BankPostgreDAO implements BankDAO{
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override 
	public void registerEmployee(Employee e) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String userSQL = "insert into \"user\" (\"user_id\", \"password\", \"type\") values (?, ?, ?);";
			
			PreparedStatement insertUser = conn.prepareStatement(userSQL);
			insertUser.setString(1, e.getUserID());
			insertUser.setInt(2, e.getPassword());
			insertUser.setString(3, e.getAccountType());
			
			
			insertUser.executeUpdate();

			String employeeSQL = "insert into \"employee\" "
					+ "(\"first_name\", \"last_name\", \"phone\", \"email\", \"address\", \"user_ref\") "
					+ "values (?, ?, ?, ?, ?, ? ) returning \"employee_id\";";
			PreparedStatement insertEmployee = conn.prepareStatement(employeeSQL);
			insertEmployee.setString(1, e.getEmpFirstName());
			insertEmployee.setString(2, e.getEmpLastName());
			insertEmployee.setString(3, e.getEmpPhone());
			insertEmployee.setString(4, e.getEmpEmail());
			insertEmployee.setString(5, e.getEmpAddress());
			insertEmployee.setString(6, e.getUserID());
			
			ResultSet rs = insertEmployee.executeQuery();
			System.out.println("~~~~~~Congratulations you account is created~~~~~~");
			BankLauncher.bankLogger.info("New Employee Registered : " + e.getEmpFirstName() + " " + e.getEmpLastName());
			
			if (rs.next()) {
				int empID = rs.getInt("employee_id");
				e.setEmpID(empID);
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
	public void registerCustomer(Customer c) {
		Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);

			String userSQL = "insert into \"user\" (\"user_id\", \"password\", \"type\") values (?, ?, ?);";
			
			PreparedStatement insertUser = conn.prepareStatement(userSQL);
			insertUser.setString(1, c.getUserID());
			insertUser.setInt(2, c.getPassword());
			insertUser.setString(3, c.getAccountType());
			
			
			insertUser.executeUpdate();

			String customerSQL = "insert into \"customer\" "
					+ "(\"first_name\", \"last_name\", \"phone\", \"email\", \"address\", \"user_ref\") "
					+ "values (?, ?, ?, ?, ?, ? ) returning \"customer_id\";";
			PreparedStatement insertCustomer = conn.prepareStatement(customerSQL);
			insertCustomer.setString(1, c.getCusFirstName());
			insertCustomer.setString(2, c.getCusLastName());
			insertCustomer.setString(3, c.getCusPhone());
			insertCustomer.setString(4, c.getCusEmail());
			insertCustomer.setString(5, c.getCusAddress());
			insertCustomer.setString(6, c.getUserID());
			
			ResultSet rs = insertCustomer.executeQuery();
			BankLauncher.bankLogger.info("New Customer Registered : " + c.getCusFirstName() + " " + c.getCusLastName());
			
			if (rs.next()) {
				int cusID = rs.getInt("customer_id");
				c.setCusID(cusID);
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
	public User login(String type, String userID, int password) throws UserNotFoundException {
		String id;
		int pass;
		Connection conn = cf.getConnection();
		try {
			String userSQL = "select * from \"user\" where \"user_id\" = ? and \"password\" = ?;";
			PreparedStatement getUser = conn.prepareStatement(userSQL);
			getUser.setString(1, userID);
			getUser.setInt(2, password);
			
			ResultSet rs = getUser.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("user_id");
				pass = rs.getInt("password");
			}else {
				throw new UserNotFoundException();
			}
			
			if(type.equals("employee")) {
				String employeeSQL = "select * from \"employee\" where \"user_ref\" = ?;";
				PreparedStatement getEmployee = conn.prepareStatement(employeeSQL);
				getEmployee.setString(1, id);
				
				ResultSet res = getEmployee.executeQuery();
				
				if(res.next()) {
					Employee e = new Employee();
					e.setUserID(id);
					e.setPassword(pass);
					e.setEmpID(res.getInt("employee_id"));
					e.setEmpFirstName(res.getString("first_name"));
					e.setEmpLastName(res.getString("last_name"));
					e.setEmpPhone(res.getString("phone"));
					e.setEmpEmail(res.getString("email"));
					e.setEmpAddress(res.getString("address"));
					return e;
				}else {
					throw new UserNotFoundException();
				}
			}else {
				String customerSQL = "select * from \"customer\" where \"user_ref\" = ?;";
				PreparedStatement getCustomer = conn.prepareStatement(customerSQL);
				getCustomer.setString(1, id);
				
				ResultSet res = getCustomer.executeQuery();
				
				if(res.next()) {
					Customer c = new Customer();
					c.setUserID(id);
					c.setPassword(pass);
					c.setCusID(res.getInt("customer_id"));
					c.setCusFirstName(res.getString("first_name"));
					c.setCusLastName(res.getString("last_name"));
					c.setCusPhone(res.getString("phone"));
					c.setCusEmail(res.getString("email"));
					c.setCusAddress(res.getString("address"));
					return c;
				}else {
					throw new UserNotFoundException();
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			cf.releaseConnection(conn);
		}
		return null;
	}

}
