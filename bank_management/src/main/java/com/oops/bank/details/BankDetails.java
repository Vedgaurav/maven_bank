package com.oops.bank.details;

import java.io.Serializable;
import java.util.List;

public class BankDetails implements Serializable{
	
	
	private static final long serialVersionUID = -936403451682792627L;

	private String bankName = "SBI";
	
	private List<BranchDetails> branchDetails = null;
	private List<CustomerDetails> customerDetails = null;
	private List<RateOfInterestDetails> roiDetails = null;
	
	
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<BranchDetails> getBranchDetails() {
		return branchDetails;
	}
	public void setBranchDetails(List<BranchDetails> branchDetails) {
		this.branchDetails = branchDetails;
	}
	public List<CustomerDetails> getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(List<CustomerDetails> customerDetails) {
		this.customerDetails = customerDetails;
	}
	public List<RateOfInterestDetails> getRoiDetails() {
		return roiDetails;
	}
	public void setRoiDetails(List<RateOfInterestDetails> roiDetails) {
		this.roiDetails = roiDetails;
	}
	
	
}
