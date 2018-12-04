package com.oops.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oops.bank.dao.AccountDao;
import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dbutil.DBUtility;
import com.oops.bank.details.AccountDetails;
import com.oops.bank.details.CustomerDetails;

public class CustomerDaoImpl implements CustomerDao{
	
	 Connection conn = null;
	 PreparedStatement createCustomerPrepStmt = null;
	 PreparedStatement getCustomerPrepStmt = null;
	 PreparedStatement deactivateCustomerPrepStmt = null;
	 PreparedStatement activateCustomerPrepStmt = null;
	 PreparedStatement getCustomerByPanPrepStmt = null;
	 PreparedStatement getCustomerByAdhaarPrepStmt = null;
	 PreparedStatement getCustHistoryPrepStmt = null;
	 
	 PreparedStatement getInactiveCustomersPrepStmt = null;
	 PreparedStatement getActiveCustomersPrepStmt = null;
	 
	 ResultSet resultSet = null;
	 AccountDao accountDao = null;
	 
	 
	
	public CustomerDaoImpl()
	{
		conn = DBUtility.getConnect();
	}
	
	
	/**
	 * create the customer by getting customer details object and inserts into database and returns it's cif
	 * @param cd
	 * @return
	 */

	public int createCustomer(CustomerDetails customerDetails)
	{
		int cif = 0;
		
		if(customerDetails != null)
		{
			if(createCustomerPrepStmt == null)
			{
			try {
				
				 createCustomerPrepStmt = conn.prepareStatement("insert into customer_details(customer_name,customer_type,pan_no,"
															  + "adhaar_no) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				 } 
			catch (SQLException e) {
				
				if (e instanceof SQLIntegrityConstraintViolationException) {
			        
					System.out.println(e.getMessage()+"\nHistory of this customer exists...\nSearch by Pan Number or Adhaar No");
					
			    }
				else
				System.out.println(e.getMessage());
			}
			}
			
			try {
				createCustomerPrepStmt.setString(1,customerDetails.getCustomerName());
				createCustomerPrepStmt.setString(2, customerDetails.getCustomerType());
				createCustomerPrepStmt.setString(3, customerDetails.getPanNumber());
				createCustomerPrepStmt.setString(4, customerDetails.getAdhaarNumber());
				
				
				int i = createCustomerPrepStmt.executeUpdate();
				
				if(i>0)
				{
					 resultSet = createCustomerPrepStmt.getGeneratedKeys();
				
				
				if(resultSet.next())
				{
					cif = resultSet.getInt(1);
				}
				}
				
				
				} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
			
		}
		
	
		return cif;
		
		
	}
	
	/**
	 * @param cif
	 * @return
	 * 
	 * get customer details by it's cif (including all it's active accounts
	 */

	public CustomerDetails getCustomer(int cif)
	{
		CustomerDetails customerDetails = null;
		if(accountDao == null)
			accountDao = new AccountDaoImpl();
		
			
			if(getCustomerPrepStmt == null){
		
			try {
				 getCustomerPrepStmt = conn.prepareStatement("select * from customer_details where cif = ? && active_status = 'true'");
				
				} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
			}
			
			 try {
				 
				 getCustomerPrepStmt.setInt(1,cif);
				
				 resultSet = getCustomerPrepStmt.executeQuery();
				
				if(resultSet.next())
				{
					customerDetails = new CustomerDetails(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
												resultSet.getString(5),resultSet.getString(6));
					
					
					
				}
				
				if(customerDetails!=null)
				customerDetails.setAccountList(accountDao.getAccount(cif));
			
				
				
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
				
				
			return customerDetails;
		
	}
	
	/**
	 * @return
	 * returns the array list object having all the customer details object
	 */

	public ArrayList<CustomerDetails> getInactiveCustomers()
	{
		
		ArrayList<CustomerDetails> customerList=new ArrayList<CustomerDetails>();
		
		
			if(getInactiveCustomersPrepStmt==null){
		
		try {
				getInactiveCustomersPrepStmt =conn.prepareStatement("select * from customer_details where active_status = 'false'");
		
			
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
			}
			
			
			 try {
				resultSet = getInactiveCustomersPrepStmt.executeQuery();
				//System.out.println(resultSet.getString(2));
				while(resultSet.next())
				{
					customerList.add(new CustomerDetails(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
									resultSet.getString(5),resultSet.getString(6)));
				}
				
				
				
			} catch (SQLException e) {
			
				System.out.println(e.getMessage());
			}
				
				
	
		return customerList;
	}
	
	
	public ArrayList<CustomerDetails> getActiveCustomers()
	{
		
		ArrayList<CustomerDetails> customerList=new ArrayList<CustomerDetails>();
		AccountDao accountDao = new AccountDaoImpl();
		
		if(getActiveCustomersPrepStmt == null){
		try {
			
			getActiveCustomersPrepStmt = conn.prepareStatement("select * from customer_details where active_status = 'true'");
		
			
		
		
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		

		 try {
			resultSet= getActiveCustomersPrepStmt.executeQuery();
			
			
			while(resultSet.next())
			{
				customerList.add(new CustomerDetails(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
														resultSet.getString(5),resultSet.getString(6)));
			}
			
			if(customerList!=null)
			{
				for(CustomerDetails customerDetails: customerList)
				{
					customerDetails.setAccountList(accountDao.getAccount(customerDetails.getCif()));
				}
			}
		
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		return customerList;
	}


	
	public boolean deactivateCustomer(int cif) {
		
		boolean bool = false;
		if(accountDao == null)
			accountDao = new AccountDaoImpl();
		
		if(deactivateCustomerPrepStmt == null){
		
		try {
					deactivateCustomerPrepStmt = conn.prepareStatement("update customer_details set active_status = 'false' where cif = ?");
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		

		try {
			deactivateCustomerPrepStmt.setInt(1, cif);
			

			int i = deactivateCustomerPrepStmt.executeUpdate();


			ArrayList<AccountDetails> accountlist = accountDao.getAccount(cif);

			for(AccountDetails adetails:accountlist)
			{
				accountDao.deactivateAccount(adetails.getAccountNumber());
			}
			
			if(i>0)
				bool = true;
			
			
			
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}



		return bool;
	}



	public boolean activateCustomer(int cif) {
		
		boolean bool = false;
		
		if(activateCustomerPrepStmt == null){
		
		try {
				activateCustomerPrepStmt = conn.prepareStatement("update customer_details set active_status = 'true' where cif = ?");
			
			
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		}
		

		try {
			activateCustomerPrepStmt.setInt(1, cif);
			
			int i = activateCustomerPrepStmt.executeUpdate();
			
			if((i>0))
				bool = true;
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		return bool;
	}


	
	public CustomerDetails getCustomerByPan(String panNo) {
		
			CustomerDetails customerDetails = null;
			
			if(getCustomerByPanPrepStmt == null){
			
		
			try {
				
				getCustomerByPanPrepStmt = conn.prepareStatement("select * from customer_details where pan_no = ?");
				
				
				} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
			}
			
			
			try {
				getCustomerByPanPrepStmt.setString(1,panNo);
				
				ResultSet resultSet = getCustomerByPanPrepStmt.executeQuery();
				
				if(resultSet.next())
				{
					customerDetails = new CustomerDetails(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
												resultSet.getString(5),resultSet.getString(6));
					
					
					
				}
				
				
			} catch (SQLException e) {
			
				System.out.println(e.getMessage());
			}
			
			
			
			return customerDetails;
	}



	public CustomerDetails getCustomerByAdhaar(String adhaarNo) {
		
		CustomerDetails customerDetails = null;
		
		if(getCustomerByAdhaarPrepStmt == null){
		
	
		try {
			
			getCustomerByAdhaarPrepStmt = conn.prepareStatement("select * from customer_details where adhaar_no = ?");
			
			
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		
		try {
			getCustomerByAdhaarPrepStmt.setString(1,adhaarNo);
			
			ResultSet resultSet = getCustomerByAdhaarPrepStmt.executeQuery();
			
			if(resultSet.next())
			{
				customerDetails = new CustomerDetails(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
											resultSet.getString(5),resultSet.getString(6));
				
				
				
			}
			
			
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
		
		
		return customerDetails;
	}



	public CustomerDetails getCustHistory(int cif) {
		
		CustomerDetails customerDetails = null;
		
		if(accountDao == null)
				accountDao = new AccountDaoImpl();
		
			if(getCustHistoryPrepStmt == null){
		
			try {
					getCustHistoryPrepStmt = conn.prepareStatement("select * from customer_details where cif = ?");
				
				
				} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
			}
			
			
			try {
				getCustHistoryPrepStmt.setInt(1,cif);
				
				ResultSet resultSet = getCustHistoryPrepStmt.executeQuery();
				
				if(resultSet.next())
				{
					customerDetails = new CustomerDetails(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
												resultSet.getString(5),resultSet.getString(6));
					
				}
				
				if(customerDetails!=null)
				customerDetails.setAccountList(accountDao.getAccHistory(cif));
				
				
				
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
			
			
			return customerDetails;
		
		
	}
	
	public void closePrepStatementCustomer()
	{
		
			try {
				
				if(createCustomerPrepStmt != null)
				createCustomerPrepStmt.close();
				
				if(getCustomerPrepStmt != null)
					 getCustomerPrepStmt.close();
				 
				if(deactivateCustomerPrepStmt != null)
					  deactivateCustomerPrepStmt.close();
				  
				if(activateCustomerPrepStmt != null)
					  activateCustomerPrepStmt.close();
				  
				if(getCustomerByPanPrepStmt != null)
					  getCustomerByPanPrepStmt.close();
				  
				if(getCustomerByAdhaarPrepStmt != null)
					 getCustomerByAdhaarPrepStmt.close();
				 
				if(getCustHistoryPrepStmt != null)
					 getCustHistoryPrepStmt.close();
				
				if(getInactiveCustomersPrepStmt != null)
					getInactiveCustomersPrepStmt.close();
				
				  if(getActiveCustomersPrepStmt != null)
					  getActiveCustomersPrepStmt.close();
				 
				if(resultSet != null)
					 resultSet.close();
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
		 
	}
}
