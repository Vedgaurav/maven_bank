package com.oops.bank.account.impl;


import com.oops.bank.account.Account;
import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dao.RateOfInterestDao;
import com.oops.bank.dao.impl.CustomerDaoImpl;
import com.oops.bank.dao.impl.RateOfInterestDaoImpl;
import com.oops.bank.details.AccountDetails;
import com.oops.bank.details.CustomerDetails;

/**
 * @author
 *savings account is a type of account and its calculation of interest is different
 */
public class SavingsAccount implements Account {

	private double savingsRoi = 0;
	
	RateOfInterestDao roiDao = new RateOfInterestDaoImpl();
	CustomerDao customerDao = new CustomerDaoImpl();
	CustomerDetails customer = null;
	

	public double calInterest(AccountDetails ad) {
		
		double interest = 0;
		
		
		
		
		savingsRoi = roiDao.getRoi(ad.getAccountType());
				customer = customerDao.getCustomer(ad.getCif());
				
			 if(customer.getCustomerType().equals("staff"))
				 savingsRoi = savingsRoi +1;
				else if (customer.getCustomerType().equals("srcitizen"))
					savingsRoi = savingsRoi + 0.5;
				else if(customer.getCustomerType().equals("staffsrcitizen"))
					savingsRoi = savingsRoi + 1.5;
			
			interest = (ad.getAmount() * savingsRoi * ad.getDays())/100;
			
		
			
		
	
		return interest;
	}

	
	
	

}
