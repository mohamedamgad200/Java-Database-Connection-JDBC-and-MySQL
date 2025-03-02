package com.jdbctest.storedprocedures;

import java.sql.*;

public class GetEmployeesForDepartment {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement myStmt = null;
		ResultSet myRs = null;
		String URL = "jdbc:mysql://localhost:3306/demo";
		String USER = "student";
		String PASS = "student";
		try {
			// Get a connection to database
			myConn = DriverManager.getConnection(URL, USER, PASS);

			String theDepartment = "Engineering";

			// Prepare the stored procedure call
			myStmt = myConn.prepareCall("{call get_employees_for_department(?)}");

			// Set the parameters
			myStmt.setString(1, theDepartment);

			// Call stored procedure
			System.out.println("Calling stored procedure.  get_employees_for_department('" + theDepartment + "')");
			myStmt.execute();
			System.out.println("Finished calling stored procedure.\n");

			// Get the result set
			myRs = myStmt.getResultSet();

			// Display the result set
			display(myRs);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

	}

	private static void display(ResultSet myRs) throws SQLException {
		// Process result set
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");

			System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
		}

	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private static void close(Statement myStmt, ResultSet myRs) throws SQLException {

		close(null, myStmt, myRs);
	}

}
