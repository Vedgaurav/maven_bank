
package com.oops.bank.management;

import com.oops.bank.dao.BranchDao;
import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dao.RateOfInterestDao;
import com.oops.bank.dao.impl.BranchDaoImpl;
import com.oops.bank.dao.impl.CustomerDaoImpl;
import com.oops.bank.dao.impl.RateOfInterestDaoImpl;
import com.oops.bank.details.BankDetails;


/**
 * @author Ved
 * 
 * Populates all information of Bank as an object of BankDetails
 *
 */
public class BankMgmt {
	
	
	
	BankDetails bankDetails = new BankDetails();
	CustomerDao customerDao = new CustomerDaoImpl();
	BranchDao branchDao = new BranchDaoImpl();
	RateOfInterestDao roiDao = new RateOfInterestDaoImpl();
	
	/**
	 * @return
	 * It returns the BankDetails object by which we can get all the customers, branches, rate of interests detail.
	 */
	public BankDetails getBankDetails()
	{
		
		
		bankDetails.setCustomerDetails(customerDao.getActiveCustomers());
		bankDetails.setBranchDetails(branchDao.getBranches());
		bankDetails.setRoiDetails(roiDao.getRateOfInterests());
		
		return bankDetails;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
