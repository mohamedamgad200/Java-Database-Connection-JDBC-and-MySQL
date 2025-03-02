package com.jdbctest;

import java.sql.*;

public class JdbcPreparedStatementTest {
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement myStmt = null;
		ResultSet myRes = null;
		String URL = "jdbc:mysql://localhost:3306/demo";
		String PASSWORD = "student";
		String USERNAME = "student";
		try {
			// 1. Get a connection to database
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			// 2. Prepare statement
			myStmt = conn.prepareStatement("select * from employees where salary > ? and department=?");

			// 3. Set the parameters
			myStmt.setDouble(1, 80000);
			myStmt.setString(2, "Legal");

			// 4. Execute SQL query
			myRes = myStmt.executeQuery();

			// 5. Display the result set
			display(myRes);
			
			//
			// Reuse the prepared statement:  salary > 25000,  department = HR
			//
			
			System.out.println("\n\nReuse the prepared statement:  salary > 25000,  department = HR");
			
			myStmt.setDouble(1, 25000);
			myStmt.setString(2, "HR");
			
			// 7. Execute SQL query
			myRes = myStmt.executeQuery();
			
			// 8. Display the result set
			display(myRes);

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if (myRes != null) {
				myRes.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		}
	}

	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}
}
