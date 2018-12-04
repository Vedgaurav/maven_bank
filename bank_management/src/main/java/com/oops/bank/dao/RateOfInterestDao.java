package com.oops.bank.dao;

import com.oops.bank.details.RateOfInterestDetails;
import java.util.ArrayList;

public interface RateOfInterestDao {
	
	/**
	 * @param roi
	 * changes rate  of interest according to account type in rate of interest object
	 */
	public void changeRoi(RateOfInterestDetails roi);
	
	/**
	 * @param roi
	 * Inserts a new entry for interest
	 */
	public void createRoi(RateOfInterestDetails roi);
	
	/**
	 * @param accountType
	 * @return
	 * get rate of interest of that accounttype
	 */
	public double getRoi(String accountType);
	
	/**
	 * @return
	 * Retrieves all the account types and its rate of interest
	 */
	public ArrayList<RateOfInterestDetails> getRateOfInterests();
	
	/**
	 * closes all the prepared statement and result set
	 */
	public void closePrepStmtRateOfInterest();
	

}
