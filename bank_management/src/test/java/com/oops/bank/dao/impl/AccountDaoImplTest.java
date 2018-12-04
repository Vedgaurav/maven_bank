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
import com.oops.bank.dao.AccountDao;
import com.oops.bank.details.AccountDetails;


// this is a change check

@RunWith(MockitoJUnitRunner.class)
public class AccountDaoImplTest {
	
	
	
	
		
		
		@InjectMocks
		private AccountDao accountDao = new AccountDaoImpl();
	  
	    @Mock
	    private Connection conn;
	    @Mock
	    private PreparedStatement stmt;
	    @Mock
	    private ResultSet resultSet;
	   
	 
	    
	    private ArrayList<AccountDetails> accountList;
	    
	    @Before
	    public void setUp() throws Exception {
	    	
	        

	 
	        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
	        
	        when(conn.prepareStatement(anyString(),anyInt())).thenReturn(stmt);
	        
	       
	    
	        int cif = 1000;
	        int accountNo = 50000;
	        String accountType = "savings";
	        double amount = 3000;
	        int branchId = 1;
	        String activeStatus = "true";
	        
	        double interest = 30.0;
	        int days = 365;
	        
	         accountList = new ArrayList<AccountDetails>();
	        
	     //  CustomerDetails customerDetails =  new CustomerDetails(cif,customerName,customerType,panNo,adhaarNo,activeStatus);
	        
	        ArrayList <ArrayList<Object>> accList= new ArrayList<ArrayList<Object>>();  
	        
	        
	     
	        
	        for(int i=0;i<=2;i++)
	        {
	        	 ArrayList<Object> alist = new ArrayList<Object>();
	        	 
	        	 if(i==1)
	              	accountType = "fd";
	              if(i==2)
	              	accountType = "homeloan";
	        	 
	        alist.add(accountNo+i);
	        alist.add(accountType);
	        alist.add(amount);
	        alist.add(interest);
	        alist.add(days);
	        alist.add(cif);
	        alist.add(branchId+i);
	        alist.add(activeStatus);
	        
	        
	        accList.add(alist);
	        
	        accountList.add( new AccountDetails(accountNo+i,accountType,amount,cif,branchId+i,activeStatus));
	        }
	       
	        MockResultSet resultSet= new MockResultSet("mockRs");
	        
	     
	        
	        resultSet.addColumn("acc_no");
	        resultSet.addColumn("acc_type");
	        resultSet.addColumn("amount");
	        resultSet.addColumn("interest");
	        resultSet.addColumn("days");
	        resultSet.addColumn("cif");
	        resultSet.addColumn("branch_id");
	        resultSet.addColumn("active_status");
	        
	      

	      for(ArrayList<Object> al: accList)
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
	    public void createAccountTest() throws Exception
	    {
	    	 
		         
		         //when(stmt.executeUpdate()).thenReturn(1);
		         
		         int accountNo =  accountDao.createAccount(accountList.get(0));
				
				 assertEquals(accountList.get(0).getAccountNumber(),accountNo);
	         
	    }
	    
	    @Test
	    public void displayAccountTest()
	    {
	    	accountDao.displayAccount();
	    }
	    
	    @Test 
	    public void getAccountTest()
	    {
	    	
	    	ArrayList<AccountDetails> accountListActual = accountDao.getAccount(1000);
	    	
	    	int i = 0;
	    	
	    	for(AccountDetails accountDetailsExpected: accountList)
	    	{
	    		AccountDetails accountDetailsActual = accountListActual.get(i);
	    		
	    	assertEquals(accountDetailsExpected.getAccountNumber(),accountDetailsActual.getAccountNumber());
			assertEquals(accountDetailsExpected.getAccountType(), accountDetailsActual.getAccountType());
			assertEquals(accountDetailsExpected.getAmount(), accountDetailsActual.getAmount(),0.0);
			assertEquals(accountDetailsExpected.getCif(),accountDetailsActual.getCif());
			assertEquals(accountDetailsExpected.getBranchId(), accountDetailsActual.getBranchId());
			assertEquals(accountDetailsExpected.getActiveStatus(), accountDetailsActual.getActiveStatus());
			
			i++;
	    	}
	    }
	    
	    
	    @Test
	    public void getAccInBranchTest()
	    {
	    	ArrayList<AccountDetails> accountListActual = accountDao.getAccInBranch(1);
	    	
	    	int i = 0;
	    	
	    	for(AccountDetails accountDetailsExpected: accountList)
	    	{
	    		AccountDetails accountDetailsActual = accountListActual.get(i);
	    		
	    	assertEquals(accountDetailsExpected.getAccountNumber(),accountDetailsActual.getAccountNumber());
			assertEquals(accountDetailsExpected.getAccountType(), accountDetailsActual.getAccountType());
			assertEquals(accountDetailsExpected.getAmount(), accountDetailsActual.getAmount(),0.0);
			assertEquals(accountDetailsExpected.getCif(),accountDetailsActual.getCif());
			assertEquals(accountDetailsExpected.getBranchId(), accountDetailsActual.getBranchId());
			assertEquals(accountDetailsExpected.getActiveStatus(), accountDetailsActual.getActiveStatus());
			
			i++;
	    	}
	    }
	    
	    @Test
	    public void getAccHistoryTest()
	    {
	    	ArrayList<AccountDetails> accountListActual = accountDao.getAccHistory(1000);
	    	
	    	int i = 0;
	    	
	    	for(AccountDetails accountDetailsExpected: accountList)
	    	{
	    		AccountDetails accountDetailsActual = accountListActual.get(i);
	    		
	    	assertEquals(accountDetailsExpected.getAccountNumber(),accountDetailsActual.getAccountNumber());
			assertEquals(accountDetailsExpected.getAccountType(), accountDetailsActual.getAccountType());
			assertEquals(accountDetailsExpected.getAmount(), accountDetailsActual.getAmount(),0.0);
			assertEquals(accountDetailsExpected.getCif(),accountDetailsActual.getCif());
			assertEquals(accountDetailsExpected.getBranchId(), accountDetailsActual.getBranchId());
			assertEquals(accountDetailsExpected.getActiveStatus(), accountDetailsActual.getActiveStatus());
			
			i++;
	    	}
	    }
	    
	    @Test
	    public void getAccountDetailsTest()
	    {
	    	AccountDetails accountDetailsActual = accountDao.getAccountDetail(50000);
	    	
	    	
	    	AccountDetails accountDetailsExpected = accountList.get(0);
	   
	    		
	    	assertEquals(accountDetailsExpected.getAccountNumber(),accountDetailsActual.getAccountNumber());
			assertEquals(accountDetailsExpected.getAccountType(), accountDetailsActual.getAccountType());
			assertEquals(accountDetailsExpected.getAmount(), accountDetailsActual.getAmount(),0.0);
			assertEquals(accountDetailsExpected.getCif(),accountDetailsActual.getCif());
			assertEquals(accountDetailsExpected.getBranchId(), accountDetailsActual.getBranchId());
			assertEquals(accountDetailsExpected.getActiveStatus(), accountDetailsActual.getActiveStatus());
			
			
	    }
	    
	    	
	    }


