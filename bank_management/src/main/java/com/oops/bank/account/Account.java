package com.oops.bank.account;

import com.oops.bank.details.AccountDetails;

/**
 * @author Ved
 * we can implement current account for which calinterest() will return 0.
 *Parent class for all the account types
 */

public interface Account  {
	
	/**
	 * abstract  method used to calculate interest implementation by child classes
	 * @param accd
	 * @param sc
	 * @return
	 */
	public double calInterest(AccountDetails ad);
	
	
	
}
