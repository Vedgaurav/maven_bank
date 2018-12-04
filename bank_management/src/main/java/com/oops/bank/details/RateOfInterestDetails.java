package com.oops.bank.details;

import java.io.Serializable;

/**
 * @author JAI JAGANNATH SWAMI
 * Used to contain the account type and it's rate of interest according to the policy of the bank
 *
 */
public class RateOfInterestDetails implements Serializable {
	
	
	private static final long serialVersionUID = 2170868237244638967L;
	private String accountType;
	private double roi;
	
	
	
	public RateOfInterestDetails(String accountType, double roi) {
		
		this.accountType = accountType;
		this.roi = roi;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getRoi() {
		return roi;
	}

	public void setRoi(double roi) {
		this.roi = roi;
	}
	
	
	
}
