package com.jdbctest;

import java.sql.*;

public class JdbcDeleteTest {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStat = null;
		ResultSet myRes = null;
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";
		try {
			// Get a connection to database
			myConn = DriverManager.getConnection(dbUrl, user, pass);
			System.out.println("Database connected successful! \n");

			// Create a statement
			myStat = myConn.createStatement();

			// Call helper method to display the employee's information
			System.out.println("BEFORE THE DELETE...");
			displayEmployee(myConn, "John", "Doe");
			// DELETE the employee
			System.out.println("\nDELETEINTG THE EMPLOYEE: John Doe\n");
			int rowAffected = myStat
					.executeUpdate("delete from employees " + "where last_name='Doe' and first_name='John'");
			// Call helper method to display the employee's information
			System.out.println("AFTER THE DELETE...");
			displayEmployee(myConn, "John", "Doe");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (myRes != null) {
				myRes.close();
			}
			if (myStat != null) {
				myStat.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}
	}

	private static void displayEmployee(Connection conn, String first_name, String last_name) {
		ResultSet myRes = null;
		PreparedStatement myStmt = null;
		try {
			// Prepare statement
			myStmt = conn.prepareStatement(
					"select last_name,first_name,email from employees where last_name=? and first_name=?");
			myStmt.setString(1, last_name);
			myStmt.setString(2, first_name);
			// Execute SQL query
			myRes = myStmt.executeQuery();

			// Process result set
			while (myRes.next()) {
				String theLastName = myRes.getString("last_name");
				String theFirstName = myRes.getString("first_name");
				String email = myRes.getString("email");

				System.out.printf("%s %s, %s\n", theFirstName, theLastName, email);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void close(Connection conn, Statement myStat, ResultSet myRes) throws SQLException {
		if (myRes != null) {
			myRes.close();
		}
		if (myStat != null) {
			myStat.close();
		}

		if (conn != null) {
			conn.close();
		}
	}

	private static void close(Statement myStat, ResultSet myRes) throws SQLException {
		close(null, myStat, myRes);
	}
}
