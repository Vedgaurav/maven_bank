package com.oops.bank.account.impl;

import java.sql.Connection;

import com.oops.bank.account.Account;
import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dao.RateOfInterestDao;
import com.oops.bank.dao.impl.CustomerDaoImpl;
import com.oops.bank.dao.impl.RateOfInterestDaoImpl;
import com.oops.bank.dbutil.DBUtility;
import com.oops.bank.details.AccountDetails;
import com.oops.bank.details.CustomerDetails;



/**
 * @author Ved
 *
 *All the specific logic related to Fd account can be implemented 
 *We have calculated the interest according to Fd
 */
public class FdAccount implements Account{
	
	private double fdRoi = 0;
	Connection conn = null;
	
	
	public FdAccount()
	{
		conn = DBUtility.getConnect();
	}
	
	
	RateOfInterestDao roiDao = new RateOfInterestDaoImpl();
	CustomerDao customerDao = new CustomerDaoImpl();
	CustomerDetails customer = null;
	
	/* (non-Javadoc)
	 * @see com.oops.bank.accountmanagement.Account#calInterest(com.oops.bank.details.AccountDetails)
	 * Here the business logic is si but in real life they may have different logics
	 */
	public double calInterest(AccountDetails ad) {
		
		double interest = 0;
		
		
		
		
		fdRoi = roiDao.getRoi(ad.getAccountType());
				customer = customerDao.getCustomer(ad.getCif());
				
			 if(customer.getCustomerType().equals("staff"))
					fdRoi = fdRoi + 2;
				else if (customer.getCustomerType().equals("srcitizen"))
					fdRoi = fdRoi + 1;
				else if(customer.getCustomerType().equals("staffsrcitizen"))
					fdRoi = fdRoi + 2.5;
			
			interest = (ad.getAmount() * fdRoi * ad.getDays())/100;
			
		return interest;
	}
	
	
	

}
