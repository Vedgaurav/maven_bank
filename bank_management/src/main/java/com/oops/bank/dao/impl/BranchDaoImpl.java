package com.oops.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.oops.bank.dao.AccountDao;
import com.oops.bank.dao.BranchDao;
import com.oops.bank.dbutil.DBUtility;
import com.oops.bank.details.BranchDetails;

public class BranchDaoImpl implements BranchDao {
	
	Connection conn = null;
	PreparedStatement createBranchPrepStmt = null;
	PreparedStatement getBranchPrepStmt = null;
	PreparedStatement deactivateBranchPrepStmt = null;
	PreparedStatement updateBranchPrepStmt = null;
	
	PreparedStatement getBranchesPrepStmt = null;
	
	ResultSet resultSet = null;
	
	AccountDao accountDao = null;
	
	public BranchDaoImpl()
	{
		conn = DBUtility.getConnect();
	}
	
	/**
	 * @param b
	 * @return
	 * Takes the object of BranchDetails and fetches all info and inserts it into the database and returns the branch ID
	 */
	
	public int createBranch(BranchDetails b)
	{
		int branchId = 0;
		
		if(createBranchPrepStmt == null)
		{
		
		try {
			
			createBranchPrepStmt = conn.prepareStatement("insert into branch(branch_name,location) "
										+ "values(?,?)",Statement.RETURN_GENERATED_KEYS);
		
		}
	 catch (SQLException e) {
		
		System.out.println(e.getMessage());
	}
		}
		
	
	try {
		
		createBranchPrepStmt.setString(1, b.getBranchName());
		createBranchPrepStmt.setString(2, b.getLocation());
		
	
		int i = createBranchPrepStmt.executeUpdate();
		
		if(i>0)
		{
			 resultSet = createBranchPrepStmt.getGeneratedKeys();
		
		
		if(resultSet.next())
		{
			branchId = resultSet.getInt(1);
		}
		}
			
		} catch (SQLException e) {
		
		System.out.println(e.getMessage());
	}
	
	
	
		return branchId;
}
	
	
	/**
	 * Returns ArrayList object having all the BranhDetails object in it(Including all the active accounts in that branch)
	 */
	
	public ArrayList<BranchDetails> getBranches()
	{
		ArrayList<BranchDetails> branchList=new ArrayList<BranchDetails>();
		
		if(accountDao == null)
		accountDao = new AccountDaoImpl();
		
		if(getBranchesPrepStmt == null){
		
		try {
				getBranchesPrepStmt =conn.prepareStatement("select * from branch");
		
			
		
		
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		 try {
			resultSet= getBranchesPrepStmt.executeQuery();
			
			while(resultSet.next())
			{
				branchList.add(new BranchDetails(resultSet.getInt("branch_id"),resultSet.getString("branch_name"),resultSet.getString("location"),resultSet.getString("active_status")));
			}
			
			if(branchList!=null)
			{
				for(BranchDetails branchDetails:branchList)
				{
					branchDetails.setAccountList(accountDao.getAccInBranch(branchDetails.getBranchId()));
				}
			}
		
			
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
			
			
		return branchList;
	
		
	}
	
	/**
	 * @param branchId
	 * @return
	 * It returns the object of BranchDetails class having all the information of the branchId provided.
	 */

	public BranchDetails getBranch(int branchId)
	{
		BranchDetails branchDetails = null;
		
		if(accountDao == null)
				accountDao = new AccountDaoImpl();
		
		if(getBranchPrepStmt == null) {
		
		try {
			
			getBranchPrepStmt = conn.prepareStatement("select * from branch where branch_id = ? && active_status = 'true'");
			
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			getBranchPrepStmt.setInt(1, branchId);
			
			 resultSet=getBranchPrepStmt.executeQuery();
				
				if(resultSet.next())
				{
					branchDetails = new BranchDetails(resultSet.getInt("branch_id"),resultSet.getString("branch_name"),resultSet.getString("location"),resultSet.getString("active_status"));
				}
				
				if(branchDetails!=null)
				branchDetails.setAccountList(accountDao.getAccInBranch(branchId));
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
		return branchDetails;
	
	}

	
	public boolean deactivateBranch(int oldBranchId, int newBranchId) {
		
		boolean bool = false;
		
		if(deactivateBranchPrepStmt == null)
		{
		
			try {
				
				deactivateBranchPrepStmt = conn.prepareStatement("update account_details set branch_id = ? where branch_id = ?");
				
				} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
		}
			
			try {
				
				deactivateBranchPrepStmt.setInt(1, newBranchId);
				
				deactivateBranchPrepStmt.setInt(2, oldBranchId);
				
				int i = deactivateBranchPrepStmt.executeUpdate();
				if(i>0)
					System.out.println("All accounts transferred to new branch Id: "+newBranchId);
				else
					System.out.println("No accounts transferred");
				
				
				
			} catch (SQLException e1) {
				
				System.out.println(e1.getMessage());
			}
			
			if(updateBranchPrepStmt == null) {
			try {
				
				updateBranchPrepStmt = conn.prepareStatement("update branch set active_status = 'false' where branch_id = ?");
				
				} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
		}
			
			try {
				updateBranchPrepStmt.setInt(1, oldBranchId);

				int j = updateBranchPrepStmt.executeUpdate();
				
				if(j>0)
					bool = true;
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
			
			return bool;
		
	}


	public void closePreparedStatementBranch() {
	
		
			try {
				
				if(createBranchPrepStmt != null)
				createBranchPrepStmt.close();
				
				if(getBranchPrepStmt != null)
					getBranchPrepStmt.close();
				
				if(deactivateBranchPrepStmt != null)
					deactivateBranchPrepStmt.close();
				
				if(updateBranchPrepStmt != null)
					updateBranchPrepStmt.close();
				
				if(getBranchesPrepStmt != null)
					getBranchesPrepStmt.close();
				
				if(resultSet != null)
					resultSet.close();
				
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		
		
	}
	
	

}
