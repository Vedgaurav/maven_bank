package com.oops.bank.factory;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


import com.oops.bank.account.impl.FdAccount;
import com.oops.bank.account.impl.HomeLoanAccount;
import com.oops.bank.account.impl.SavingsAccount;


@RunWith(MockitoJUnitRunner.class)
public class AccountFactoryTest{
	
	

	
	AccountFactory accountFactory = new AccountFactory();
	
	@Test
	public void getAccountTest()
	{
		
		

	
	
		
		assertEquals(SavingsAccount.class,accountFactory.getAccount("savings").getClass());
	
		
		assertEquals(FdAccount.class,accountFactory.getAccount("fd").getClass());
		
		assertEquals(HomeLoanAccount.class,accountFactory.getAccount("homeloan").getClass());
		
		
		
		
	}
	
	
	
	
	
	
	
}
