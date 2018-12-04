package com.oops.bank.details;

import java.io.Serializable;
import java.util.List;

/**
 * We can add here multiple fields like customer address, phno, father's customerName ec
 * Similarly we can also add all the ID types supported by bank for eg voter id, driving license etc;
 *
 */
public class CustomerDetails implements Serializable {

	private static final long serialVersionUID = -3094492816824259575L;
	private int cif;
	private String customerName;
	private String customerType;
	private String adhaarNumber;
	private String panNumber;
	private String activeStatus;
	
	private  List<AccountDetails> accountList =null;
	
	public CustomerDetails() {
		
	}

	
	
	public CustomerDetails(int cif, String customerName, String customerType, String panNumber, String adhaarNumber,
			String activeStatus) {
		
		this.cif = cif;
		this.customerName = customerName;
		this.customerType = customerType;
		this.adhaarNumber = adhaarNumber;
		this.panNumber = panNumber;
		this.activeStatus = activeStatus;
	}


	

	public CustomerDetails(String customerName, String customerType, String panNumber, String adhaarNumber) {
		
		
		this.customerName = customerName;
		this.customerType = customerType;
		this.panNumber = panNumber;
		this.adhaarNumber = adhaarNumber;
	}


	






	

	public int getCif() {
		return cif;
	}


	public void setCif(int cif) {
		this.cif = cif;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerType() {
		return customerType;
	}


	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}


	public String getAdhaarNumber() {
		return adhaarNumber;
	}


	public void setAdhaarNumber(String adhaarNumber) {
		this.adhaarNumber = adhaarNumber;
	}


	public String getPanNumber() {
		return panNumber;
	}


	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}


	public List<AccountDetails> getAccountList() {
		return accountList;
	}


	public void setAccountList(List<AccountDetails> accountList) {
		this.accountList = accountList;
	}
	
	public String getActiveStatus() {
		return activeStatus;
	}


	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	
}
