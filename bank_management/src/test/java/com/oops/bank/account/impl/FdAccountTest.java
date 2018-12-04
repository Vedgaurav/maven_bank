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
public class FdAccountTest{
	
	@InjectMocks
	Account account = new FdAccount();
	
   
	@Mock
	RateOfInterestDao roiDao = new RateOfInterestDaoImpl();
   
	@Mock
	CustomerDao customerDao = new CustomerDaoImpl();
	
   

	
	@Test
	public void testCalculateInterestFdAccount1()
	{
		
		
		CustomerDetails customerDetails = new  CustomerDetails(1000,"gaurav","staff","pan1","ad1","true");
		
		
		
		when(roiDao.getRoi(any(String.class))).thenReturn(8.0);
		
		
		when(customerDao.getCustomer(any(Integer.class))).thenReturn(customerDetails);
	
		
		AccountDetails accountDetails = new AccountDetails();
		
		accountDetails.setAccountType("fd");
		accountDetails.setAmount(5000);
		accountDetails.setDays(3);
		accountDetails.setCif(1002);
		
		
		
		
		double interest = account.calInterest(accountDetails);
		
		assertEquals(1500,interest,0.0);
		
	}
	
	@Test
	public void testCalculateInterestFdAccount2()
	{
		
		
		CustomerDetails customerDetails = new  CustomerDetails(1001,"gaurav","srcitizen","pan1","ad1","true");
		
		
		
		when(roiDao.getRoi(any(String.class))).thenReturn(8.0);
		
		
		when(customerDao.getCustomer(any(Integer.class))).thenReturn(customerDetails);
	
		
		AccountDetails accountDetails = new AccountDetails();
		
		accountDetails.setAccountType("fd");
		accountDetails.setAmount(5000);
		accountDetails.setDays(3);
		accountDetails.setCif(1002);
		
		
		
		
		double interest = account.calInterest(accountDetails);
		
		assertEquals(1350,interest,0.0);
		
	}
	
	@Test
	public void testCalculateInterestFdAccount3()
	{
		
		
		CustomerDetails customerDetails = new  CustomerDetails(1001,"gaurav","staffsrcitizen","pan1","ad1","true");
		
		
		
		when(roiDao.getRoi(any(String.class))).thenReturn(8.0);
		
		
		when(customerDao.getCustomer(any(Integer.class))).thenReturn(customerDetails);
	
		
		AccountDetails accountDetails = new AccountDetails();
		
		accountDetails.setAccountType("savings");
		accountDetails.setAmount(5000);
		accountDetails.setDays(3);
		accountDetails.setCif(1002);
		
		
		
		
		double interest = account.calInterest(accountDetails);
		
		assertEquals(1575,interest,0.0);
		
	}
	
	
	
}