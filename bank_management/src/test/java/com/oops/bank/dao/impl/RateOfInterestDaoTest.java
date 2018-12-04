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

import com.oops.bank.dao.RateOfInterestDao;

import com.oops.bank.details.RateOfInterestDetails;




@RunWith(MockitoJUnitRunner.class)
public class RateOfInterestDaoTest {
	
	
	@InjectMocks
	private RateOfInterestDao rateOfInterestDao = new RateOfInterestDaoImpl();
  
    @Mock
    private Connection conn;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet resultSet;
   
 
    
    private ArrayList<RateOfInterestDetails> rateOfInterestExpectedList;
    
    @Before
    public void setUp() throws Exception {
    	
    	 
 
        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
    
        String accountType = "savings";
        double rateOfInterest = 4.0;
        
         rateOfInterestExpectedList = new ArrayList<RateOfInterestDetails>();
        
    
        
        ArrayList <ArrayList<Object>> rateOfInterestListResultSet = new ArrayList<ArrayList<Object>>();  
        
        
     
        
        	for(int i=0;i<=2;i++) {
        	 ArrayList<Object> alist = new ArrayList<Object>();
        	 

             if(i==1)
             {
             	accountType = "fd";
             	rateOfInterest = 8.0;
             }
             if(i==2)
             {
             	accountType = "homeloan";
             	rateOfInterest = 12.0;
             }
        	 
        alist.add(accountType);
        alist.add(rateOfInterest);
       
        rateOfInterestListResultSet.add(alist);
        
        rateOfInterestExpectedList.add( new RateOfInterestDetails(accountType,rateOfInterest));
        
        
     }
   
       
        MockResultSet resultSet= new MockResultSet("mockRs");
        
     
        
        resultSet.addColumn("account_type");
        resultSet.addColumn("roi");
       
      

      for(ArrayList<Object> al: rateOfInterestListResultSet)
    	   resultSet.addRow(al);
    	   
    	   
    	 
      when(stmt.executeQuery()).thenReturn(resultSet);
     
    }
    
    @Test
    public void getRateOfInterestsTest()
    {
    	
    	ArrayList<RateOfInterestDetails> rateOfInterestListActual = rateOfInterestDao.getRateOfInterests();
    	
    	int i = 0;
    	
    	for(RateOfInterestDetails rateOfInterestExpected: rateOfInterestExpectedList)
    	{
    		RateOfInterestDetails rateOfInterestActual = rateOfInterestListActual.get(i);
    		
    		assertEquals(rateOfInterestExpected.getAccountType(),rateOfInterestActual.getAccountType());
    		assertEquals(rateOfInterestExpected.getAccountType(),rateOfInterestActual.getAccountType());
    		
    		i++;
    	}
    }
    	
    	@Test
    	public void getRoiTest()
    	{
    		double rateOfInterest = rateOfInterestDao.getRoi("savings");
        	
        	assertEquals(rateOfInterestExpectedList.get(0).getRoi(),rateOfInterest,0.0);
        	
        	
        		
    	}
    	
    	
    	
    	
    	
    	
    
}