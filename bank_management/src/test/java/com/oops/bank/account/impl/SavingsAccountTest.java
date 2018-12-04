package com.oops.bank.account.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;



import com.oops.bank.account.Account;

import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dao.RateOfInterestDao;

import com.oops.bank.dao.impl.CustomerDaoImpl;

import com.oops.bank.dao.impl.RateOfInterestDaoImpl;
import com.oops.bank.details.AccountDetails;
import com.oops.bank.details.CustomerDetails;





@RunWith(MockitoJUnitRunner.class)
public class SavingsAccountTest{
	
	@InjectMocks
	Account account = new SavingsAccount();
	
   
	@Mock
	RateOfInterestDao roiDao = new RateOfInterestDaoImpl();
   
	@Mock
	CustomerDao customerDao = new CustomerDaoImpl();
	
   

	
	@Test
	public void testCalculateInterestSavingsAccount1()
	{
		
		
		CustomerDetails customerDetails = new  CustomerDetails(1000,"gaurav","staff","pan1","ad1","true");
		
		
		
		when(roiDao.getRoi(any(String.class))).thenReturn(4.0);
		
		
		when(customerDao.getCustomer(any(Integer.class))).thenReturn(customerDetails);
	
		
		AccountDetails accountDetails = new AccountDetails();
		
		accountDetails.setAccountType("savings");
		accountDetails.setAmount(5000);
		accountDetails.setDays(3);
		accountDetails.setCif(1002);
		
		
		
		
		double interest = account.calInterest(accountDetails);
		
		assertEquals(750,interest,0.0);
		
	}
	
	@Test
	public void testCalculateInterestSavingsAccount2()
	{
		
		
		CustomerDetails customerDetails = new  CustomerDetails(1001,"gaurav","srcitizen","pan1","ad1","true");
		
		
		
		when(roiDao.getRoi(any(String.class))).thenReturn(4.0);
		
		
		when(customerDao.getCustomer(any(Integer.class))).thenReturn(customerDetails);
	
		
		AccountDetails accountDetails = new AccountDetails();
		
		accountDetails.setAccountType("savings");
		accountDetails.setAmount(5000);
		accountDetails.setDays(3);
		accountDetails.setCif(1002);
		
		
		
		
		double interest = account.calInterest(accountDetails);
		
		assertEquals(675,interest,0.0);
		
	}
	
	@Test
	public void testCalculateInterestSavingsAccount3()
	{
		
		
		CustomerDetails customerDetails = new  CustomerDetails(1001,"gaurav","staffsrcitizen","pan1","ad1","true");
		
		
		
		when(roiDao.getRoi(any(String.class))).thenReturn(4.0);
		
		
		when(customerDao.getCustomer(any(Integer.class))).thenReturn(customerDetails);
	
		
		AccountDetails accountDetails = new AccountDetails();
		
		accountDetails.setAccountType("savings");
		accountDetails.setAmount(5000);
		accountDetails.setDays(3);
		accountDetails.setCif(1002);
		
		
		
		
		double interest = account.calInterest(accountDetails);
		
		assertEquals(825,interest,0.0);
		
	}
	
	
	
}