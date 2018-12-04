package com.oops.bank.account.impl;



import com.oops.bank.account.Account;
import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dao.RateOfInterestDao;
import com.oops.bank.dao.impl.CustomerDaoImpl;
import com.oops.bank.dao.impl.RateOfInterestDaoImpl;
import com.oops.bank.details.AccountDetails;
import com.oops.bank.details.CustomerDetails;


/**
 * @author Ved
 *For now only interest is calculated according to homeloan
 */
public class HomeLoanAccount implements Account
{
	private double homeRoi = 0;
	
	RateOfInterestDao roiDao = new RateOfInterestDaoImpl();
	CustomerDao customerDao = new CustomerDaoImpl();
	CustomerDetails customer = null;
	
	
	public double calInterest(AccountDetails ad) {
		
		
double interest = 0;
		
		
	
		
		homeRoi = roiDao.getRoi(ad.getAccountType());
				customer = customerDao.getCustomer(ad.getCif());
				
			 if(customer.getCustomerType().equals("staff"))
					homeRoi = homeRoi - 4;
				else if (customer.getCustomerType().equals("srcitizen"))
					homeRoi = homeRoi - 0.5;
				else if(customer.getCustomerType().equals("staffsrcitizen"))
					homeRoi = homeRoi - 1.5;
			
			interest = (ad.getAmount() * homeRoi * ad.getDays())/100;
			
		
			
		
	
		return interest;
	}

	
	
	

}
