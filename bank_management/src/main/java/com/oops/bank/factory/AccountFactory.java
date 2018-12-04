package com.oops.bank.factory;

import com.oops.bank.account.Account;
import com.oops.bank.account.impl.FdAccount;
import com.oops.bank.account.impl.HomeLoanAccount;
import com.oops.bank.account.impl.SavingsAccount;

/**
 * @author Ved
 * For giving the object according to the account type
 *
 */
public class AccountFactory {

	/**
	 * @param acctype
	 * @return
	 * 
	 * Returns the object of the account type class in its parent class(Account) reference 
	 */
	public Account getAccount(String acctype)
	{
		
		
		 if (acctype.equalsIgnoreCase("Savings"))
			 return new SavingsAccount();
		else if(acctype.equalsIgnoreCase("Fd"))
			return new FdAccount();
		else if(acctype.equalsIgnoreCase("HomeLoan"))
			return new HomeLoanAccount();
		else
		return null;
		
	}
}
