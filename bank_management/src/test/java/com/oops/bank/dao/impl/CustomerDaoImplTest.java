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
import com.oops.bank.dao.CustomerDao;

import com.oops.bank.details.CustomerDetails;




@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoImplTest {
	
	
	@InjectMocks
	private CustomerDao customerDao = new CustomerDaoImpl();
  
    @Mock
    private Connection conn;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet resultSet;
   
 
    
    private ArrayList<CustomerDetails> custList;
    
    @Before
    public void setUp() throws Exception {
    	
    	 
 
        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
    
        int cif = 1000;
        String customerName = "gaurav";
        String customerType = "staff";
        String panNo = "pan1";
        String adhaarNo = "ad1";
        String activeStatus = "true";
        
         custList = new ArrayList<CustomerDetails>();
        
     //  CustomerDetails customerDetails =  new CustomerDetails(cif,customerName,customerType,panNo,adhaarNo,activeStatus);
        
        ArrayList <ArrayList<Object>>customerList = new ArrayList<ArrayList<Object>>();  
        
        
     
        
     for(int i=0;i<=2;i++) {
        	 ArrayList<Object> alist = new ArrayList<Object>();
        	 

             if(i==1)
             	customerType = "srcitizen";
             if(i==2)
             	customerType = "staffsrcitizen";
        	 
        alist.add(cif+i);
        alist.add(customerName+i);
        alist.add(customerType);
        alist.add(panNo+i);
        alist.add(adhaarNo+i);
        alist.add(activeStatus);
        
        
        customerList.add(alist);
        
        custList.add( new CustomerDetails(cif+i,customerName+i,customerType,panNo+i,adhaarNo+i,activeStatus));
        
        
     }
   
       
        MockResultSet resultSet= new MockResultSet("mockRs");
        
     
        
        resultSet.addColumn("cif");
        resultSet.addColumn("customer_name");
        resultSet.addColumn("customer_type");
        resultSet.addColumn("pan_no");
        resultSet.addColumn("adhaar_no");
        resultSet.addColumn("active_status");
        
      

      for(ArrayList<Object> al: customerList)
    	   resultSet.addRow(al);
    	   
    	   
    	 
    	
    	/*   while(resultSet.next())
   		{
    		
    		   
    		  System.out.println(resultSet.getString("cif")+"\n"+resultSet.getString(2));
   			
   		}*/
    	   
        when(stmt.executeQuery()).thenReturn(resultSet);
     
    }
   
    /*@Test
    public void createCustomerTest() throws Exception
    {
    	 
	         
	         when(stmt.executeUpdate()).thenReturn(1);
	         
	         when(stmt.getGeneratedKeys()).thenReturn(resultSet);
			
	         when(resultSet.first()).thenReturn(true);
			 when(resultSet.getInt(1)).thenReturn(1000);
			 
			int cif =  customerDaoImpl.createCustomer(customerDetails);
			
			 assertEquals(1000,cif,0);
         
    }*/
    
    
    
    @Test
    public void getCustomerTest() throws Exception
    {
    	
    	
    	CustomerDetails customerDetails = customerDao.getCustomer(1000);
    	
    	CustomerDetails customerDetailsCheck = custList.get(0);
    	
    	/*assertEquals(customerDetails,customerDetailsCheck);*/
    	
    		
    	assertEquals(customerDetailsCheck.getCif(),customerDetails.getCif());
		assertEquals(customerDetailsCheck.getCustomerName(), customerDetails.getCustomerName());
		assertEquals(customerDetailsCheck.getCustomerType(), customerDetails.getCustomerType());
		assertEquals(customerDetailsCheck.getPanNumber(),customerDetails.getPanNumber());
		assertEquals(customerDetailsCheck.getAdhaarNumber(), customerDetails.getAdhaarNumber());
		assertEquals(customerDetailsCheck.getActiveStatus(), customerDetails.getActiveStatus());
    	
    }
    
    @Test
    public void getInactiveCustomersTest() throws Exception
    {
    	
    	
    	ArrayList<CustomerDetails> customerList = customerDao.getInactiveCustomers();
    	
    	
    	int i = 0;
    	

    	for(CustomerDetails customerDetailsCheck: custList)
    	{
    		CustomerDetails customerDetails = customerList.get(i);	
    	assertEquals(customerDetailsCheck.getCif(),customerDetails.getCif());
		assertEquals(customerDetailsCheck.getCustomerName(), customerDetails.getCustomerName());
		assertEquals(customerDetailsCheck.getCustomerType(), customerDetails.getCustomerType());
		assertEquals(customerDetailsCheck.getPanNumber(),customerDetails.getPanNumber());
		assertEquals(customerDetailsCheck.getAdhaarNumber(), customerDetails.getAdhaarNumber());
		assertEquals(customerDetailsCheck.getActiveStatus(), customerDetails.getActiveStatus());
		
		i++;
		
    		}
    		
    	
    	
    	
    }
    
    @Test
    public void getActiveCustomersTest() throws Exception
    {
    	
    	ArrayList<CustomerDetails> customerList = customerDao.getActiveCustomers();
    	
    	int i = 0;
    		
    	
    	
    	for(CustomerDetails customerDetailsCheck: custList)
    	{
    		CustomerDetails customerDetails = customerList.get(i);	
    	assertEquals(customerDetailsCheck.getCif(),customerDetails.getCif());
		assertEquals(customerDetailsCheck.getCustomerName(), customerDetails.getCustomerName());
		assertEquals(customerDetailsCheck.getCustomerType(), customerDetails.getCustomerType());
		assertEquals(customerDetailsCheck.getPanNumber(),customerDetails.getPanNumber());
		assertEquals(customerDetailsCheck.getAdhaarNumber(), customerDetails.getAdhaarNumber());
		assertEquals(customerDetailsCheck.getActiveStatus(), customerDetails.getActiveStatus());
		
		i++;
		
    		}
    	}
    
    
    
    	@Test
    	public void getCustomerByPanTest() throws Exception
    	{
    		CustomerDetails customerDetails = customerDao.getCustomerByPan("pan10");
    		
    		CustomerDetails customerDetailsCheck = custList.get(0);
        	
        	assertEquals(customerDetailsCheck.getCif(),customerDetails.getCif());
    		assertEquals(customerDetailsCheck.getCustomerName(), customerDetails.getCustomerName());
    		assertEquals(customerDetailsCheck.getCustomerType(), customerDetails.getCustomerType());
    		assertEquals(customerDetailsCheck.getPanNumber(),customerDetails.getPanNumber());
    		assertEquals(customerDetailsCheck.getAdhaarNumber(), customerDetails.getAdhaarNumber());
    		assertEquals(customerDetailsCheck.getActiveStatus(), customerDetails.getActiveStatus());
        	
    		
    		
    	}
    	
    	@Test
    	public void getCustomerByAdhaarTest() throws Exception
    	{
    		CustomerDetails customerDetails = customerDao.getCustomerByAdhaar("ad10");
    		
    		CustomerDetails customerDetailsCheck = custList.get(0);
        	
        	assertEquals(customerDetailsCheck.getCif(),customerDetails.getCif());
    		assertEquals(customerDetailsCheck.getCustomerName(), customerDetails.getCustomerName());
    		assertEquals(customerDetailsCheck.getCustomerType(), customerDetails.getCustomerType());
    		assertEquals(customerDetailsCheck.getPanNumber(),customerDetails.getPanNumber());
    		assertEquals(customerDetailsCheck.getAdhaarNumber(), customerDetails.getAdhaarNumber());
    		assertEquals(customerDetailsCheck.getActiveStatus(), customerDetails.getActiveStatus());
    	}
    	
    	@Test
    	public void getCustHistoryTest() throws Exception
    	{
    		CustomerDetails customerDetails = customerDao.getCustomer(1000);
        	
        	CustomerDetails customerDetailsCheck = custList.get(0);
        	
        
        	
        		
        	assertEquals(customerDetailsCheck.getCif(),customerDetails.getCif());
    		assertEquals(customerDetailsCheck.getCustomerName(), customerDetails.getCustomerName());
    		assertEquals(customerDetailsCheck.getCustomerType(), customerDetails.getCustomerType());
    		assertEquals(customerDetailsCheck.getPanNumber(),customerDetails.getPanNumber());
    		assertEquals(customerDetailsCheck.getAdhaarNumber(), customerDetails.getAdhaarNumber());
    		assertEquals(customerDetailsCheck.getActiveStatus(), customerDetails.getActiveStatus());
    	}
    
    }
    
    
