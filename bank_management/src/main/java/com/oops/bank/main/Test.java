package com.oops.bank.main;


import java.util.Scanner;

import com.oops.bank.account.Account;
import com.oops.bank.dao.AccountDao;
import com.oops.bank.dao.BranchDao;
import com.oops.bank.dao.CustomerDao;
import com.oops.bank.dao.RateOfInterestDao;
import com.oops.bank.dao.impl.AccountDaoImpl;
import com.oops.bank.dao.impl.BranchDaoImpl;
import com.oops.bank.dao.impl.CustomerDaoImpl;
import com.oops.bank.dao.impl.RateOfInterestDaoImpl;
import com.oops.bank.dbutil.DBUtility;
import com.oops.bank.details.AccountDetails;
import com.oops.bank.details.BankDetails;
import com.oops.bank.details.BranchDetails;
import com.oops.bank.details.CustomerDetails;
import com.oops.bank.details.RateOfInterestDetails;
import com.oops.bank.factory.AccountFactory;
import com.oops.bank.management.BankMgmt;

/**
 * @author JAI JAGANNATH SWAMI
 *Using to test 
 */
public class Test {
	

	
	public static void main(String[] args) {
		
	
		Scanner scanner = new Scanner(System.in);
		
		
		
		CustomerDao customerDao= null;
		AccountDao accountDao = null;
		BranchDao branchDao = null;
		RateOfInterestDao rateOfInterestDao = null;
		
		BankMgmt bankMgmt = null;
		AccountFactory factoryAccount = null;
		Account account = null;
		
		
		

		
		while(true)
		{
			
			System.out.println("\n1.Create Customer\n2.Create Account\n3.Display Customer by Cif and all the Accounts opened"
					+"\n4.Delete Customer\n5.Search Customer By PanNo\n6.Search Customer By adhaarNo\n7.Activate Customer"
					+"\n8.Customer Account History\n9.Show Inactive Customers\n10.Display All Customer\n\n11.Display All Account"
					+ "\n12.Delete Account\n13.Activate Account\n\n14.Create Branch\n15.Get All Branch Details"
					+"\n16.Show Accounts by Branch Id\n17.Delete Branch\n\n18.Show Rate Of Interests\n19.Insert Rate Of Interest"
					+"\n20.Change Rate Of Interest\n21Get Account Details by Account No\n22.Exit");
					
			
	
			
			int option = scanner.nextInt();
			
			
			if(customerDao == null)
				customerDao = new CustomerDaoImpl();
			
			if(branchDao == null)
				branchDao = new BranchDaoImpl();
			
			if(factoryAccount == null)
				factoryAccount = new AccountFactory();
			
			if(accountDao == null)
				accountDao = new AccountDaoImpl();
			
			if(rateOfInterestDao == null)
				rateOfInterestDao = new RateOfInterestDaoImpl();
			
			if(bankMgmt == null)
				bankMgmt = new BankMgmt();
		
			
			
			switch(option)
			{
				case 1:
					
					System.out.println("Enter Name");
					String name = scanner.next();
					System.out.println("Enter Customer Type (\n1.staff\n2.srcitizen\n3.staffsrcitizen)");
					int typeSelect = scanner.nextInt();
					
					String type = null;
					
					if(typeSelect ==1)
						type = "staff";
					else if(typeSelect == 2)
						type = "srcitizen";
					else if(typeSelect == 3)
						type = "staffsrcitizen";
					else 
						break;
					
					
					if((!"staff".equals(type))&&!"srcitizen".equals(type)&&!"staffsrcitizen".equals(type)){
					
						System.out.println("Wrong Customer Type...");
						break;
					}
		
						
					System.out.println("Pan card :");
					String panno = scanner.next();	
					System.out.println("Aadhar no :");
					String adharno = scanner.next();
					
			
					int id = customerDao.createCustomer(new CustomerDetails(name, type, panno, adharno));
					if(id!=0)
						System.out.println("Customer created cif: "+id);
					else
					{
						System.out.println("Customer creation failed...");
						break;
					}
					
					
					
					
				case 2:
					
					boolean bool = true;
					
					while(bool) {
						
					int days = 0;
					System.out.println("Enter cif");
					int cif = scanner.nextInt();
					
					if((customerDao.getCustomer(cif)!=null)) {
					if((customerDao.getCustomer(cif)).getActiveStatus().equals("false"))
					{
						System.out.println("Customer cif does not exist");
						continue;

					}
					}
					else
					{
						System.out.println("Customer does not exist");
						continue;
					}
					
					System.out.println("Enter Branch Id");
					
					for(BranchDetails branchDet:branchDao.getBranches())
					{	
						if(branchDet.getActiveStatus().equals("true"))
						System.out.println("\nBranchId: "+branchDet.getBranchId()+"\nBranch Name: "+branchDet.getBranchName()+"\nLocation : "
								+branchDet.getLocation());
						
					}
					
					int branchId = scanner.nextInt();
					
					if((branchDao.getBranch(branchId))!=null) {
					if((branchDao.getBranch(branchId)).getActiveStatus().equals("false"))
					{
						System.out.println("Branch does not exist");
						continue;
					}
					}
					else
					{
						System.out.println("Branch does not exist");
						continue;
					}
					
					String accountType = null;
					
					System.out.println("Enter type of account \n1.savings\n2.fd\n3.homeloan");
					
					int accountTypeSelect = scanner.nextInt();
					
					if(accountTypeSelect ==1)
					accountType = "savings";
					else if(accountTypeSelect == 2)
						accountType = "fd";
					else if(accountTypeSelect ==3)
						accountType = "homeloan";
					else
						continue;
					
					
					
					
					if(accountType.equals("savings")||accountType.equals("fd")||accountType.equals("homeloan")) {
					
					if(!"savings".equals(accountType))
					{
						System.out.println("Enter Years");
							days = scanner.nextInt();
					}
					System.out.println("Enter Amount");
					double amount = scanner.nextDouble();
					
					
					AccountDetails accountDetails = new AccountDetails(cif,branchId,accountType,amount,days);
					
					 account = factoryAccount.getAccount(accountType);
					
					int accountNo = accountDao.createAccount(accountDetails);
					
					accountDetails.setAccountNumber(accountNo);
					
					if(accountNo!=0) {
					System.out.println("Account Created Successfully accno is: "+accountNo);
					
					double interest = account.calInterest(accountDetails);
					
					accountDetails.setInterest(interest);
					
					accountDao.updateInterest(accountDetails);
					
					if(interest!=0.0)
						System.out.println("Interest amt: "+interest);
					
					bool = false;
					}
					}
					else
						System.out.println("Account type does not exist...");
						continue;
					}
				break;
					
				case 3:
					
					
					
					System.out.println("Enter cif");
					
				
					CustomerDetails customerDetails = customerDao.getCustomer(scanner.nextInt());
					
					if(customerDetails==null)
					{
						System.out.println("Customer does not exist");
						break;
					}
					
					System.out.println("\ncif: "+customerDetails.getCif()+"\nName: "+customerDetails.getCustomerName()+"\nCustomer Type: "
										+customerDetails.getCustomerType()+"\nPanno: "+customerDetails.getPanNumber()+"\nAdharno: "
										+customerDetails.getAdhaarNumber()+"\nActive Status: "+customerDetails.getActiveStatus());
					
					for(AccountDetails accountDet: customerDetails.getAccountList())
					{
						System.out.println("\ncif: "+accountDet.getCif()+"\nAcc No: "+accountDet.getAccountNumber()+"\nAccount Type: "
								+accountDet.getAccountType()+"\nAmount: "+accountDet.getAmount()+"\nInterest: "+accountDet.getInterest()+"\nYear: "+accountDet.getDays()
								+"\nBranch Id: "+accountDet.getBranchId()+"\nActive Status: "+accountDet.getActiveStatus());
						
					}
					
					break;
				case 4:
					
					
					
					System.out.println("Enter cif to be deleted");
					
					
					CustomerDetails customerDetail = customerDao.getCustomer(scanner.nextInt());
					
					if(customerDetail == null)
					{
						System.out.println("Customer does not exist");
						break;
						
					}
					
					if(customerDao.deactivateCustomer(customerDetail.getCif()))
						System.out.println("Customer deleted");
										
					break;
				case 5:
					
					
					
					System.out.println("Enter Pan No");
					
					customerDetails = customerDao.getCustomerByPan(scanner.next());
					
					if(customerDetails==null)
					{
						System.out.println("Pan does not exist");
						break;
					}
					
					System.out.println("\ncif: "+customerDetails.getCif()+"\nName: "+customerDetails.getCustomerName()+"\nCustomer Type: "
							+customerDetails.getCustomerType()+"\nPanno: "+customerDetails.getPanNumber()+"\nAdharno: "
							+customerDetails.getAdhaarNumber()+"\nActive Status: "+customerDetails.getActiveStatus());
					
					break;
				case 6:
					
				
						
					System.out.println("Enter adhaar No");
					
					customerDetails = customerDao.getCustomerByAdhaar(scanner.next());
					
					if(customerDetails==null)
					{
						System.out.println("Adhaar does not exist");
						break;
					}
					
					System.out.println("\ncif: "+customerDetails.getCif()+"\nName: "+customerDetails.getCustomerName()
										+"\nCustomer Type: "+customerDetails.getCustomerType()+"\nPanno: "
										+customerDetails.getPanNumber()+"\nAdharno: "+customerDetails.getAdhaarNumber()
										+"\nActive Status: "+customerDetails.getActiveStatus());
					
					break;
				case 7:
					
					
					
					System.out.println("Activate Customer\nEnter cif");
					
					if(customerDao.activateCustomer(scanner.nextInt()))
						System.out.println("Customer Activated");
					else
						System.out.println("Customer does not exist");
					
					break;
				case 8:
					
					
					System.out.println("Enter cif");
					
					customerDetails = customerDao.getCustHistory(scanner.nextInt());
					
					if(customerDetails==null)
					{
						System.out.println("Customer does not exist");
						break;
					}
					
					System.out.println("\ncif: "+customerDetails.getCif()+"\nName: "+customerDetails.getCustomerName()+"\nCustomer Type: "
									    +customerDetails.getCustomerType()+"\nPanno: "+customerDetails.getPanNumber()+"\nAdharno: "
									    +customerDetails.getAdhaarNumber()+"\nActive Status: "+customerDetails.getActiveStatus());
					
					for(AccountDetails accountDetails8: customerDetails.getAccountList())
					{
						System.out.println("\ncif: "+accountDetails8.getCif()+"\nAcc No: "+accountDetails8.getAccountNumber()+"\nAccount Type: "
								+accountDetails8.getAccountType()+"\nAmount: "+accountDetails8.getAmount()+"\nInterest: "+accountDetails8.getInterest()
								+"\nYear: "+accountDetails8.getDays()+"\nBranch Id: "+accountDetails8.getBranchId()+"\nActive Status: "+accountDetails8.getActiveStatus());
						
					}
					break;
				case 9:
					
				
					
					for(CustomerDetails customerDetails9: customerDao.getInactiveCustomers())
					{
						System.out.println("\ncif: "+customerDetails9.getCif()+"\nName: "+customerDetails9.getCustomerName()+"\nCustomer Type: "
								+customerDetails9.getCustomerType()+"\nPanno: "+customerDetails9.getPanNumber()+"\nAdharno: "+customerDetails9.getAdhaarNumber()
								+"\nActive Status: "+customerDetails9.getActiveStatus());
						
					}
					
					break;
				case 10:
						
						
					
					BankDetails bankDetails = bankMgmt.getBankDetails();
					
					System.out.println("Bank Name: "+bankDetails.getBankName());
					
					for(CustomerDetails customerDetails10: bankDetails.getCustomerDetails())
					{
						System.out.println("\ncif: "+customerDetails10.getCif()+"\nName: "+customerDetails10.getCustomerName()+"\nCustomer Type: "
								+customerDetails10.getCustomerType()+"\nPanno: "+customerDetails10.getPanNumber()+"\nAdharno: "+customerDetails10.getAdhaarNumber()
								+"\nActive Status: "+customerDetails10.getActiveStatus());
						
					}
					
					break;
				case 11:
					
						accountDao.displayAccount();
						
					break;
				case 12:
					
					
					
					System.out.println("Delete Account\nEnter Account No");
					
					if(accountDao.deactivateAccount(scanner.nextLong()))
						System.out.println("Account Deleted");
					else
						System.out.println("Account does not exist..");
					
					break;
				case 13:
					
					
					
					System.out.println("Activate Account\nEnter Cif and Account No");
					int cif13 = scanner.nextInt();
					long accountNo13 = scanner.nextLong();
					
					if(accountDao.activateAccount(cif13,accountNo13))
						System.out.println("�ccount Activated");
					else
						System.out.println("Account Not Activated...");
					
					break;
				case 14:
					
					
					System.out.println("Enter Branch Name and Location");
					String branchName = scanner.next();
					String branchLoc = scanner.next();
					
					BranchDetails branchDetails14 = new BranchDetails(branchName,branchLoc);
					
					int branchId14 = branchDao.createBranch(branchDetails14);
					if(branchId14!=0)
						System.out.println("New Branch Created BranchId: "+branchId14);
					else
						System.out.println("branch not created");
					
					break;
				case 15:
					
					
					
					BankDetails bankDetails15 = bankMgmt.getBankDetails();
					
					System.out.println("Bank Name: "+bankDetails15.getBankName());
					
					for(BranchDetails b: bankDetails15.getBranchDetails())
					{
						System.out.println("\nBranchId: "+b.getBranchId()+"\nBranch Name: "+b.getBranchName()+"\nLocation : "
								+b.getLocation()+"\nActive Status: "+b.getActiveStatus());
						
					}
					
					break;
				case 16:
					
					
					
					
					System.out.println("enter branch id");
					
					BranchDetails branchDetails16 = branchDao.getBranch(scanner.nextInt());
					
					if(branchDetails16 ==null)
					{
						System.out.println("Branch does not exist");
						break;
					}

					for(AccountDetails accountDetails16: branchDetails16.getAccountList())
					{
						System.out.println("\ncif: "+accountDetails16.getCif()+"\nAcc No: "+accountDetails16.getAccountNumber()+"\nAccount Type: "
								+accountDetails16.getAccountType()+"\nAmount: "+accountDetails16.getAmount()+"\nInterest: "+accountDetails16.getInterest()
								+"\nYear: "+accountDetails16.getDays()+"\nBranch Id: "+accountDetails16.getBranchId()+"\nActive Status: "+accountDetails16.getActiveStatus());
						
					}
				
					
					
					break;
				case 17:
						
					
					System.out.println("Enter Branch Id to be deleted");
					int oldBranchId = scanner.nextInt();
					
					if((branchDao.getBranch(oldBranchId))!=null) {
						if((branchDao.getBranch(oldBranchId)).getActiveStatus().equals("false"))
						{
							System.out.println("Branch does not exist please provide valid Branch Id");
							break;
						}
						}
						else
						{
							System.out.println("Branch does not exist please provide valid Branch Id");
							break;
						}
					
					System.out.println("Enter New Branch Id for all the account in this branch");
					int newBranchId = scanner.nextInt();
					
					if((branchDao.getBranch(newBranchId))!=null) {
						if((branchDao.getBranch(newBranchId)).getActiveStatus().equals("false"))
						{
							System.out.println("New Branch does not exist please provide valid Branch Id");
							break;
						}
						}
						else
						{
							System.out.println("New Branch does not exist please provide valid Branch Id");
							break;
						}
			
					if(branchDao.deactivateBranch(oldBranchId, newBranchId))
						System.out.println("Branch Deleted...");
					else
						System.out.println("Branch deletion failed...");
					
					
					break;
				case 18:
					
						
						
					BankDetails bankDetails18 = bankMgmt.getBankDetails();
					
					System.out.println("Bank Name: "+bankDetails18.getBankName());
					
					for(RateOfInterestDetails roi: bankDetails18.getRoiDetails())
					{
						System.out.println("\nAccount Type: "+roi.getAccountType()+"\nRate Of Interest: "+roi.getRoi());
						
					}
					
					break;
				case 19:
					
					
					System.out.println("Enter Account Type");
					String accountType = scanner.next();
					System.out.println("Enter Rate Of Interest");
					double roi = scanner.nextDouble();
					
					rateOfInterestDao.createRoi(new RateOfInterestDetails(accountType,roi));
					
					break;
				case 20:
					
					System.out.println("Enter Account Type");
					String accountType12 = scanner.next();
					System.out.println("Enter Rate Of Interest");
					double roi12 = scanner.nextDouble();
					
					rateOfInterestDao.changeRoi(new RateOfInterestDetails(accountType12,roi12));
					
					break;
				case 21:
					
					System.out.println("Enter Account No");
					
					AccountDetails accountDetail = accountDao.getAccountDetail(scanner.nextLong());
					
					if(accountDetail == null)
					{
						System.out.println("Account does not exist");
						break;
					}
					
					System.out.println("\ncif: "+accountDetail.getCif()+"\nAcc No: "+accountDetail.getAccountNumber()+"\nAccount Type: "
							+accountDetail.getAccountType()+"\nAmount: "+accountDetail.getAmount()+"\nInterest: "+accountDetail.getInterest()
							+"\nYear: "+accountDetail.getDays()+"\nBranch Id: "+accountDetail.getBranchId()+"\nActive Status: "
							+accountDetail.getActiveStatus());
					
					
					break;
				case 22:
					
					
						
					customerDao.closePrepStatementCustomer();
					branchDao.closePreparedStatementBranch();
					accountDao.closePreparedStatementAccount();
					rateOfInterestDao.closePrepStmtRateOfInterest();
					
					scanner.close();
					DBUtility.closeConnection();
					
					System.exit(0);
					
				default:
						System.out.println("Wrong choice!!!");
					
					
					 
					
					
				
			}
			
			
		}
		
		
		
		
	
	}
	
	
	
}
	

