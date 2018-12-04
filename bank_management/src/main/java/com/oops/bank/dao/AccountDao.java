package com.oops.bank.dao;

import java.util.ArrayList;

import com.oops.bank.details.AccountDetails;

public interface AccountDao {
	
	/**
	 * @param adetails
	 * @return
	 * Inserts all account data in database and returns the generated accno
	 */
	public int createAccount(AccountDetails aDetails);
	
	/**
	 * Displays all account
	 */
	public void displayAccount();
	
	
	/**
	 * @param cif
	 * Get all the account related to provided cif
	 * @return
	 */
	public ArrayList<AccountDetails> getAccount(int cif);
	
	
	/**
	 * @param cif
	 * @return
	 * gets all  active and deactive accounts of the customer by cif
	 */
	public ArrayList<AccountDetails> getAccHistory(int cif);
	
	/**
	 * @param brid
	 * Returns all the account related to that particular branch Id
	 * @return
	 */
	public ArrayList<AccountDetails> getAccInBranch(int brid);
	
	/**
	 * @param interest
	 * @param accno
	 * @return
	 * Update Interest in the database according to account no
	 */
	public boolean updateInterest(AccountDetails accountDetails);
	
	/**
	 * @param accno
	 * @return
	 * sets the active status of that account false
	 */
	public boolean deactivateAccount(long accno);
	
	/**
	 * @param accno
	 * @return
	 * sets the active status of that account true
	 */
	public boolean activateAccount(int cif,long accno);
	
	/**
	 * @param accno
	 * @return
	 * get account information by account no
	 */
	public AccountDetails getAccountDetail(long accno);
	
	
	/**
	 * closes all the prepared statement and result set
	 */
	public void closePreparedStatementAccount();

	
	
	

}
