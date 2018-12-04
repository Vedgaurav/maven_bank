package com.oops.bank.dao;

import com.oops.bank.details.BranchDetails;
import java.util.ArrayList;

public interface BranchDao {
	
	/**
	 * @param branchId
	 * @return
	 * get all information of branch using branch id
	 */
	public BranchDetails getBranch(int branchId);
	
	/**
	 * @return
	 * Get all the branches information
	 */
	public ArrayList<BranchDetails> getBranches();
	
	
	/**
	 * @param b
	 * @return
	 * creates a new branch in database and returns it's generated branchid
	 */
	public int createBranch(BranchDetails b);
	
	/**
	 * @param branchId
	 * @return
	 * deactivates the branch and assigns a new branch for all it's accounts
	 */
	public boolean deactivateBranch(int oldBranchId,int newBranchId);
	
	/**
	 * closes all the prepared statement and result set
	 */
	public void closePreparedStatementBranch();
	

}
