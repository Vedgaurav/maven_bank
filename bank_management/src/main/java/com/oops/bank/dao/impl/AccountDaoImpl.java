package com.oops.bank.dao.impl;

import com.oops.bank.dao.AccountDao;
import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dbutil.DBUtility;
import com.oops.bank.details.AccountDetails;
import com.oops.bank.details.CustomerDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AccountDaoImpl implements AccountDao {

	Connection conn = null;
	PreparedStatement createAccountPrepStmt = null;
	PreparedStatement getAccountPrepStmt = null;
	PreparedStatement getAccInBranchPrepStmt = null;
	PreparedStatement updateInterestPrepStmt = null;
	PreparedStatement getAccHistoryPrepStmt = null;
	PreparedStatement deactivateAccountPrepStmt = null;
	PreparedStatement activateAccountPrepStmt = null;
	PreparedStatement getAccountDetailPrepStmt = null;
	
	PreparedStatement displayAccountPrepStmt = null;
	
	ResultSet resultSet = null;
	
	
	public  AccountDaoImpl()
	{
		conn = DBUtility.getConnect();
	}
	

	/**
	 * used to create account using AccountDetails object and returns account no
	 * @param sc
	 * @return
	 */
	
	public int createAccount(AccountDetails adetails)
	{
		int accountNo = 0;
		
		if(createAccountPrepStmt==null)
		{
		try {
				createAccountPrepStmt = conn.prepareStatement("insert into account_details(cif,acc_type,amount,branch_id)"
													   + " values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			createAccountPrepStmt.setInt(1, adetails.getCif());
			createAccountPrepStmt.setString(2, adetails.getAccountType());
			createAccountPrepStmt.setDouble(3, adetails.getAmount());
			createAccountPrepStmt.setInt(4, adetails.getBranchId());
		
			
			
			int i = createAccountPrepStmt.executeUpdate();
			
			if(i>0)
			{
				resultSet = createAccountPrepStmt.getGeneratedKeys();
			
			
			if(resultSet.next())
			{
				accountNo = resultSet.getInt(1);
			}
			
			
			}	
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		
		
		
		return accountNo;
		
	}
	
	
	
	
	
	/**
	 * Displays accountListl the account details.
	 */
	
	public void displayAccount()
	{

		ArrayList<AccountDetails> accountList=new ArrayList<AccountDetails>();
		
		if(displayAccountPrepStmt == null)
		{
		try {
			
			
			displayAccountPrepStmt =conn.prepareStatement("select * from account_details inner join interest_record using(acc_no)");
		
			
		
		
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		 try {
			resultSet=displayAccountPrepStmt.executeQuery();
			
			while(resultSet.next())
			{
				accountList.add(new AccountDetails(resultSet.getInt("acc_no"),resultSet.getString("acc_type"),resultSet.getDouble("amount"),resultSet.getDouble("interest"),resultSet.getInt("days"),
											 		resultSet.getInt("cif"),resultSet.getInt("branch_id"),resultSet.getString("active_status")));
			}
			
			
			for(AccountDetails accountDetails: accountList)
			{
				System.out.println("\ncif: "+accountDetails.getCif()+"\nAcc No: "+accountDetails.getAccountNumber()+"\nAccount Type: "
									+accountDetails.getAccountType()+"\nAmount: "+accountDetails.getAmount()+"\nInterest: "+accountDetails.getInterest()
									+"\nYear: "+accountDetails.getDays()+"\nBranch Id: "+accountDetails.getBranchId()
									+"\nActive Status: "+accountDetails.getActiveStatus());
				
			}
		
			
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
			
			
		
		
	}
	
	/**
	 * Get accountListl the active accounts related to that cif
	 * @param cif
	 * @return
	 * 
	 */
	public ArrayList<AccountDetails> getAccount(int cif)
	{
		ArrayList<AccountDetails> accountList=new ArrayList<AccountDetails>();
		
		
		if(getAccountPrepStmt == null){
		try {
				getAccountPrepStmt =conn.prepareStatement("select * from account_details where cif =? && active_status = 'true'");
			
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			getAccountPrepStmt.setInt(1, cif);
			

			 resultSet=getAccountPrepStmt.executeQuery();
			
			while(resultSet.next())
			{
				accountList.add(new AccountDetails(resultSet.getInt("acc_no"),resultSet.getString("acc_type"),resultSet.getDouble("amount"),
				 		resultSet.getInt("cif"),resultSet.getInt("branch_id"),resultSet.getString("active_status")));
			}
			
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		return accountList;
	
	}
	
	/**
	 * @param brid
	 * @return
	 * Get accountListl active accounts in that branch
	 */
	
	public ArrayList<AccountDetails> getAccInBranch(int brid)
	{

		ArrayList<AccountDetails> accountList=new ArrayList<AccountDetails>();
		
		if(getAccInBranchPrepStmt == null){
		
		try {
				getAccInBranchPrepStmt = conn.prepareStatement("select * from account_details where branch_id=? && active_status = 'true'");
			
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			
			getAccInBranchPrepStmt.setInt(1, brid);
			
			

			 resultSet= getAccInBranchPrepStmt.executeQuery();
			
			while(resultSet.next())
			{
				accountList.add(new AccountDetails(resultSet.getInt("acc_no"),resultSet.getString("acc_type"),resultSet.getDouble("amount"),
				 		resultSet.getInt("cif"),resultSet.getInt("branch_id"),resultSet.getString("active_status")));
			}
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		return accountList;
	
	}


	/* (non-Javadoc)
	 * @see com.oops.bank.dao.AccountDao#updateInterest(double, long)
	 * 
	 * Update interest in database according to accno
	 */

	public boolean updateInterest(AccountDetails accountDetails) {
		
		boolean bool = false;
		
		if(updateInterestPrepStmt == null){
		
		try {
			
		updateInterestPrepStmt =  conn.prepareStatement("insert into interest_record values(?,?,?)");
		
		
	} catch (SQLException e) {
		
		System.out.println(e.getMessage());
	}
		}
		
		try {
			updateInterestPrepStmt.setInt(1,accountDetails.getAccountNumber());
			updateInterestPrepStmt.setDouble(2, accountDetails.getInterest());
			updateInterestPrepStmt.setInt(3, accountDetails.getDays());
			
			int i = updateInterestPrepStmt.executeUpdate();
			
			if(i>0)
			{
				System.out.println("Interest recorded");
				bool = true;
			}
			
			
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		return bool;
	}


	
	public ArrayList<AccountDetails> getAccHistory(int cif) {
		
		ArrayList<AccountDetails> accountList=new ArrayList<AccountDetails>();
		
		
		if(getAccHistoryPrepStmt == null){
		
		try {
				getAccHistoryPrepStmt =conn.prepareStatement("select * from account_details where cif =?");
			
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			getAccHistoryPrepStmt.setInt(1, cif);
			
			
			 resultSet=getAccHistoryPrepStmt.executeQuery();
			
			while(resultSet.next())
			{
				accountList.add(new AccountDetails(resultSet.getInt("acc_no"),resultSet.getString("acc_type"),resultSet.getDouble("amount"),
				 		resultSet.getInt("cif"),resultSet.getInt("branch_id"),resultSet.getString("active_status")));
			}
			
		} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		
		
		
		
		
		return accountList;
	}



	public boolean deactivateAccount(long accno) {
		
		boolean bool = false;
		
		if(deactivateAccountPrepStmt == null){
		
		try {
			
			
			deactivateAccountPrepStmt = conn.prepareStatement("update account_details set active_status = 'false' where acc_no= ?;");
		
		} catch (SQLException e) {
		
		System.out.println(e.getMessage());
	}
		}
		
		try {
			deactivateAccountPrepStmt.setLong(1, accno);
			
			int i = deactivateAccountPrepStmt.executeUpdate();
			
			if(i>0)
			{
				
				bool = true;
			}
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		return bool;
	}


	
	public boolean activateAccount(int cif,long accno) {

		boolean bool = false;
		
		AccountDao accountDao = new AccountDaoImpl();
		AccountDetails accountDetails = accountDao.getAccountDetail(accno);
		
		if(accountDetails == null)
		{
			System.out.println("Account Does not exist");
			return false;
		}
		
		if(accountDetails.getCif() != cif)
		{
			System.out.println("Cif of this account is different cannot activate");
			return false;
		}
		
		
		CustomerDao customerDao = new CustomerDaoImpl();
		
		
		
		
		
		CustomerDetails customerDetails = customerDao.getCustHistory(cif);
		
		if(customerDetails != null)
		{	
			
		
		if(customerDetails.getActiveStatus().equals("true"))
		{
			if(activateAccountPrepStmt == null){
			
			try {
			
			
				activateAccountPrepStmt = conn.prepareStatement("update account_details set active_status = 'true' where acc_no= ?;");
		
				} catch (SQLException e) {
		
		System.out.println(e.getMessage());
	}
			}
			

			try {
				
				activateAccountPrepStmt.setLong(1, accno);
				
				int i = activateAccountPrepStmt.executeUpdate();
				
				if(i>0)
				{
					
					bool = true;
				}
				
				
			} catch (SQLException e) {
			
				System.out.println(e.getMessage());
			}
			
			
		
		}
		else
			System.out.println("Customer does not exist please enter valid cif");
		}
		else
			System.out.println("Customer does not exist");
		
		return bool;
	}


	
	public AccountDetails getAccountDetail(long accno) {
		
		AccountDetails accountDetails= null;
		
		if(getAccountDetailPrepStmt == null){
		
		try {
				getAccountDetailPrepStmt =conn.prepareStatement("select * from account_details inner join "
																+ "interest_record using(acc_no) where acc_no = ?");
			
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			getAccountDetailPrepStmt.setLong(1,accno);
			
		 resultSet= getAccountDetailPrepStmt.executeQuery();
			
			if(resultSet.next())
			{
				accountDetails = new AccountDetails(resultSet.getInt("acc_no"),resultSet.getString("acc_type"),resultSet.getDouble("amount"),
				 		resultSet.getDouble("interest"),resultSet.getInt("days"),resultSet.getInt("cif"),
				 		resultSet.getInt("branch_id"),resultSet.getString("active_status"));
			}
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		
		return accountDetails;
	}
	
	

	
	public void closePreparedStatementAccount() {
	
		
			try {
				
				if(createAccountPrepStmt != null)
				createAccountPrepStmt.close();
				
				if(getAccountPrepStmt != null)
					getAccountPrepStmt.close();
				
				 if(getAccInBranchPrepStmt != null)
					 getAccInBranchPrepStmt.close();
				 
				 if(updateInterestPrepStmt != null)
					 updateInterestPrepStmt.close();
				 
				 if(getAccHistoryPrepStmt != null)
					 getAccHistoryPrepStmt.close();
				 
				 if(deactivateAccountPrepStmt != null)
					 deactivateAccountPrepStmt.close();
				 
				 if(activateAccountPrepStmt != null)
					 activateAccountPrepStmt.close();
				 
				 if(getAccountDetailPrepStmt != null)
					 getAccountDetailPrepStmt.close();
				 
				 if(displayAccountPrepStmt != null)
					 displayAccountPrepStmt.close();
				 
				 if(resultSet != null)
					 resultSet.close();
				
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		 
		
		
	}
	
	
}
