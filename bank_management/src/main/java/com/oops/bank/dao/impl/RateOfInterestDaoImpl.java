package com.oops.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oops.bank.dao.RateOfInterestDao;
import com.oops.bank.dbutil.DBUtility;
import com.oops.bank.details.RateOfInterestDetails;

public class RateOfInterestDaoImpl implements RateOfInterestDao {

	Connection conn = null;
	
	PreparedStatement createRoiPrepStmt = null;
	PreparedStatement changeRoiPrepStmt = null;
	PreparedStatement getRoiPrepStmt = null;
	PreparedStatement getRateOfInterestsPrepStmt = null;
	
	ResultSet resultSet = null;
	
	public RateOfInterestDaoImpl()
	{
		conn = DBUtility.getConnect();
	}
	
	
	/**
	 * @return
	 * Gets all the rate of interests of all account types.
	 */
	
	public ArrayList<RateOfInterestDetails> getRateOfInterests()
	{
		ArrayList<RateOfInterestDetails> rateOfInterestList=new ArrayList<RateOfInterestDetails>();
		
		if(getRateOfInterestsPrepStmt == null){
		
		try {
				
			getRateOfInterestsPrepStmt=conn.prepareStatement("select * from rate_of_interest");
		
			 
			} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			resultSet=getRateOfInterestsPrepStmt.executeQuery();
			
			while(resultSet.next())
			{
				rateOfInterestList.add(new RateOfInterestDetails(resultSet.getString("account_type"),resultSet.getDouble("roi")));
			}
			
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
			
		}
		
		
		
		return rateOfInterestList;
	}
	

	/**
	 * @param roi
	 * It creates a new entry of account type and its rate of interest in the database
	 */
	
	public void createRoi(RateOfInterestDetails roi)
	{
		
		if(createRoiPrepStmt == null)
		{
		try {
			createRoiPrepStmt = conn.prepareStatement("insert into rate_of_interest value(?,?)");
		}
		catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			createRoiPrepStmt.setString(1,roi.getAccountType());
			
			createRoiPrepStmt.setDouble(2, roi.getRoi());
			
			int i = createRoiPrepStmt.executeUpdate();
		
			if(i>0)
			System.out.println("Value inserted");
			else
				System.out.println("Not Inserted");
		
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
	
}
	
	/**
	 * @param roi
	 * used to change the interest rate of any give account type
	 */
	
	public void changeRoi(RateOfInterestDetails roi)
	{
		if(changeRoiPrepStmt == null)
		{
		try {
			changeRoiPrepStmt = conn.prepareStatement("update rate_of_interest set roi = ? where account_type = ?");
		
				
		}
		catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		}
		
		try {
			changeRoiPrepStmt.setString(2,roi.getAccountType());
			
			changeRoiPrepStmt.setDouble(1, roi.getRoi());
			
			int i = changeRoiPrepStmt.executeUpdate();
				
			if(i>0)
				System.out.println("Interest rate updated");
			else
				System.out.println("Not updated...");
				
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
			
	}


	
	public double getRoi(String accountType) {
		
		double rateOfInterest = 0;
		
		if(getRoiPrepStmt == null)
		{
		
		try {
			getRoiPrepStmt = conn.prepareStatement("select roi from rate_of_interest where account_type = ?");
			
			} catch (SQLException e) {
		
			System.out.println(e.getMessage());
		}
		}
		
		try {
			getRoiPrepStmt.setString(1, accountType);
			
			 resultSet = getRoiPrepStmt.executeQuery();
				
				if(resultSet.next())
				{
					rateOfInterest = resultSet.getDouble("roi");
				}
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
	
		return rateOfInterest;
}


	
	public void closePrepStmtRateOfInterest() {
		
		
			try {
				if(createRoiPrepStmt != null)
				createRoiPrepStmt.close();
				
				 if(changeRoiPrepStmt != null)
					 changeRoiPrepStmt.close();
				 
				 if(getRoiPrepStmt != null)
					 getRoiPrepStmt.close();
				 
				 if(getRateOfInterestsPrepStmt != null)
					 getRateOfInterestsPrepStmt.close();
				 
				 if(resultSet!=null)
					 resultSet.close();
				
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
		
		
		
	}
	
	
}
