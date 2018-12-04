package com.oops.bank.dao.impl;



import static org.junit.Assert.assertEquals;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.mockrunner.mock.jdbc.MockResultSet;
import com.oops.bank.dao.BranchDao;
import com.oops.bank.details.BranchDetails;


// this is a change check

@RunWith(MockitoJUnitRunner.class)
public class BranchDaoImplTest {
	
	
	
	
		
		
		@InjectMocks
		private BranchDao branchDao = new BranchDaoImpl();
	  
	    @Mock
	    private Connection conn;
	    @Mock
	    private PreparedStatement stmt;
	    @Mock
	    private ResultSet resultSet;
	   
	 
	    
	    private ArrayList<BranchDetails> branchList;
	    
	    @Before
	    public void setUp() throws Exception {
	    	
	        

	 
	        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
	        
	        when(conn.prepareStatement(anyString(),anyInt())).thenReturn(stmt);
	        
	       
	    
	        int branchId= 1;
	        
	        String branchName = "baner";
	        String location = "pune";
	        String activeStatus = "true";
	        
	    
	        
	         branchList = new ArrayList<BranchDetails>();
	        
	     //  CustomerDetails customerDetails =  new CustomerDetails(cif,customerName,customerType,panNo,adhaarNo,activeStatus);
	        
	        ArrayList <ArrayList<Object>> branchArrayList= new ArrayList<ArrayList<Object>>();  
	        
	        
	     
	        
	        for(int i=0;i<=2;i++)
	        {
	        	 ArrayList<Object> alist = new ArrayList<Object>();
	        	
	        	 
	        alist.add(branchId+i);
	        alist.add(branchName+i);
	        alist.add(location+i);
	        alist.add(activeStatus);
	        
	        
	        branchArrayList.add(alist);
	        
	        branchList.add( new BranchDetails(branchId+i,branchName+i,location+i,activeStatus));
	        }
	       
	        MockResultSet resultSet= new MockResultSet("mockRs");
	        
	     
	        
	        resultSet.addColumn("branch_id");
	        resultSet.addColumn("branch_name");
	        resultSet.addColumn("location");
	        resultSet.addColumn("active_status");
	        
	      

	      for(ArrayList<Object> al: branchArrayList)
	    	   resultSet.addRow(al);
	    	   
	    	   
	    	 
	    	
	    	/*   while(resultSet.next())
	   		{
	    		
	    		   
	    		  System.out.println(resultSet.getString("cif")+"\n"+resultSet.getString(2));
	   			
	   		}*/
	    	   
	        when(stmt.executeQuery()).thenReturn(resultSet);
	        when(stmt.getGeneratedKeys()).thenReturn(resultSet);
	        when(stmt.executeUpdate()).thenReturn(1);
	     

	    }
	    
	    @Test
	    public void createBranchTest()
	    {
	    	int branchId = branchDao.createBranch(branchList.get(0));
	    	
	    	assertEquals(branchList.get(0).getBranchId(),branchId);
	    }
	    
	    @Test
	    public void getBranchesTest()
	    {
	    	ArrayList<BranchDetails> branchListActual = branchDao.getBranches();
	    	
	    	int i = 0;
	    	
	    	for(BranchDetails branchDetailsExpected: branchList)
	    	{
	    		BranchDetails branchDetailsActual = branchListActual.get(i);
	    		
	    	assertEquals(branchDetailsExpected.getBranchId(),branchDetailsActual.getBranchId());
			assertEquals(branchDetailsExpected.getBranchName(), branchDetailsActual.getBranchName());
			assertEquals(branchDetailsExpected.getLocation(), branchDetailsActual.getLocation());
			assertEquals(branchDetailsExpected.getActiveStatus(),branchDetailsActual.getActiveStatus());
			
			i++;
	    	}
	    }
	    
	    @Test
	    public void getBranchTest()
	    {
	    	BranchDetails branchDetailsActual = branchDao.getBranch(1);
	    	
	    
	    	
	    	BranchDetails branchDetailsExpected = branchList.get(0);
	    	
	    	
	    		
	    	assertEquals(branchDetailsExpected.getBranchId(),branchDetailsActual.getBranchId());
			assertEquals(branchDetailsExpected.getBranchName(), branchDetailsActual.getBranchName());
			assertEquals(branchDetailsExpected.getLocation(), branchDetailsActual.getLocation());
			assertEquals(branchDetailsExpected.getActiveStatus(),branchDetailsActual.getActiveStatus());
			
			
	    	}
	    
	    
	    
	    }
	    
	    

