package com.jdbctest.storedprocedures;

import java.sql.*;

public class GreetTheDepartment {
	public static void main(String[] args) throws SQLException {
		Connection myConn=null;
		CallableStatement myStmt=null;
		String URL = "jdbc:mysql://localhost:3306/demo";
		String USER = "student";
		String PASS = "student";
		try {
			// Get a connection to database
			myConn=DriverManager.getConnection(URL, USER, PASS);
			
			String theDepartment = "Engineering";
			
			// Prepare the stored procedure call
			myStmt=myConn.prepareCall("{call greet_the_department(?)}");
			
			// Set the parameters
			myStmt.registerOutParameter(1, Types.VARCHAR);
			myStmt.setString(1, theDepartment);
			
			// Call stored procedure
			System.out.println("Calling stored procedure.  greet_the_department('" + theDepartment + "')");
			myStmt.execute();
			System.out.println("Finished calling stored procedure");
			
			// Get the value of the INOUT parameter
			String theResult=myStmt.getString(1);
			System.out.println("\nThe result = " + theResult);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally {
			close(myConn, myStmt);
		}
	}
	private static void close(Connection myConn, Statement myStmt) throws SQLException {
		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

}
