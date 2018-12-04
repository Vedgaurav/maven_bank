 package com.oops.bank.details;

import java.io.Serializable;

/**
 * Account type can have values like savings, current, fd, rd
 *
 */
public class AccountDetails implements Serializable{
	
	
	private static final long serialVersionUID = -1023890874428157684L;
	private int accountNumber;
	private String accountType;
	private double amount;
	private double interest;
	private int days;
	private int cif;
	private int branchId;
	private String activeStatus;
	
	public AccountDetails() {}

    public AccountDetails(int accountNumber, String accountType, double amount, double interest, int days, int cif,
			int branchId, String activeStatus) {
		
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.amount = amount;
		this.interest = interest;
		this.days = days;
		this.cif = cif;
		this.branchId = branchId;
		this.activeStatus = activeStatus;
	}



    public AccountDetails(int cif,int branchId, String accountType, double amount,int days) {
	
		this.cif = cif;
		this.accountType = accountType;
		this.amount = amount;
		this.days = days;
		this.branchId = branchId;
	}

    
    
    public AccountDetails(int accountNo,String accountType,double amount,int cif,int branchId,String activeStatus) {
    	
		this.cif = cif;
		this.accountType = accountType;
		this.amount = amount;
		this.accountNumber = accountNo;
		this.activeStatus = activeStatus;
		this.branchId = branchId;
	}

    
    
    
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getCif() {
		return cif;
	}

	public void setCif(int cif) {
		this.cif = cif;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	
	

}
