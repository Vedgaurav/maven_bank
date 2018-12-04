package com.oops.bank.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * @author Ved
 *This class is used to take connection and we want that all the database operations should be done 
 *with only one connection so we are not allowing to make object of this class
 *by keeping its constructor private
 *Any object will not be created of this class so its member and methods are kept static 
 */

public class DBUtility {
	
	private DBUtility() {
	}

	//private volatile static Connection conn = null;
	
	private static Connection conn = null;

	/**
	 * @return
	 * To take the connection. It gives the connection object if it's null it will create the connection and 
	 * If its not null it will give the same object reference which was created first time. For thread safe used synchronized
	 */
	public static  Connection  getConnect() {

		if (conn == null) {
			
			synchronized(DBUtility.class)
			{
			
				if( conn == null)
				{
			
					
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","root");                                                                                                                                        
				} catch (SQLException e) {

				System.out.println(e.getMessage());
										 }
				}
			}
		}
		
			return conn;
}
	
	/**
	 * To close the connection
	 */
	public static void closeConnection() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				System.out.println(e.getMessage());
			}
		}
	}

}
