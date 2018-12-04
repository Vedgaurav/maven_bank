
package com.oops.bank.management;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.oops.bank.dao.BranchDao;
import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dao.RateOfInterestDao;
import com.oops.bank.dao.impl.BranchDaoImpl;
import com.oops.bank.dao.impl.CustomerDaoImpl;
import com.oops.bank.dao.impl.RateOfInterestDaoImpl;
import com.oops.bank.details.BankDetails;

public class BankMgmtTest{
	
	
	@InjectMocks
	BankMgmt branchMgmt = new BankMgmt();
	
	@Mock
	CustomerDao customerDao = new CustomerDaoImpl();
	
	@Mock
	BranchDao branchDao = new BranchDaoImpl();
	
	@Mock
	RateOfInterestDao rateOfInterestDao = new RateOfInterestDaoImpl();
	
	
	
	
	@Test
	public void getBankDetailsTest()
	{
		
	}
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	

