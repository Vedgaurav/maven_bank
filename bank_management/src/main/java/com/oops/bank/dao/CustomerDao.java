package com.oops.bank.dao;

import com.oops.bank.details.CustomerDetails;
import java.util.ArrayList;

public interface CustomerDao {
	
	/**
	 * @param cd
	 * @return
	 * Creates new customer in db and returns generated cif no
	 */
	public int createCustomer(CustomerDetails cd);
	
	/**
	 * @param cif
	 * @return
	 * Gets the information of customer by its cif
	 */
	public CustomerDetails getCustomer(int cif);
	
	
	/**
	 * @return
	 * Retrieves all the inactive customers
	 */
	public ArrayList<CustomerDetails> getInactiveCustomers();
	
	/**
	 * @return
	 * Retrieves all the active customers 
	 */
	public ArrayList<CustomerDetails> getActiveCustomers();
	
	/**
	 * @param cif
	 * @return
	 * Will make the status false of that customer
	 */
	public boolean deactivateCustomer(int cif);
	
	/**
	 * @param cif
	 * @return
	 * Will make the active status of that customer true
	 */
	public boolean activateCustomer(int cif);
	
	/**
	 * @param panNo
	 * @return
	 * retrieves customer details by pan no
	 */
	public CustomerDetails getCustomerByPan(String panNo);
	
	/**
	 * @param adhaarNo
	 * @return
	 * Retrieves customer details by adhaarNo
	 */
	public CustomerDetails getCustomerByAdhaar(String adhaarNo);
	
	/**
	 * @param cif
	 * @return
	 * Returns the customer details object having all the active and deactive accounts.
	 */
	public CustomerDetails getCustHistory(int cif);
	
	/**
	 * closes all the preparedStatement object and result set object
	 */
	public void closePrepStatementCustomer();
	
	

}
