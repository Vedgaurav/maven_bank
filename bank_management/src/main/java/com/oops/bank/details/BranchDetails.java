package com.oops.bank.details;

import java.io.Serializable;
import java.util.List;

/**
 * @author JAI JAGANNATH SWAMI
 * It contains all the properties of branch like it's id name and location
 *
 */
public class BranchDetails implements Serializable {

	private static final long serialVersionUID = -8705705566742665780L;
	private int branchId;
	private String branchName;
	private String location;
	private String activeStatus;
	
	
	private List<AccountDetails> accountList;
	
	
	
	public BranchDetails(String branchName, String location) {
		
		
		this.branchName = branchName;
		this.location = location;
	}

	

	
	public BranchDetails(int branchId, String branchName, String location, String activeStatus) {
		
		this.branchId = branchId;
		this.branchName = branchName;
		this.location = location;
		this.activeStatus = activeStatus;
		
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
