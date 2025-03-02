package com.jdbctest.transactionsandmetadata;

import java.sql.*;

public class ResultSetDemo {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String URL = "jdbc:mysql://localhost:3306/demo";
		String USER = "student";
		String PASS = "student";
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(URL, USER, PASS);
			// 2. Run query
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select id, last_name, first_name, salary from employees");
			// 3. Get result set metadata
			ResultSetMetaData resultSetMetaData = myRs.getMetaData();
			// 4. Display info
			int columnCount = resultSetMetaData.getColumnCount();
			System.out.println("Column count: " + columnCount + "\n");
			for (int column = 1; column <= columnCount; column++) {
				System.out.println("Column name: " + resultSetMetaData.getColumnName(column));
				System.out.println("Column type name: " + resultSetMetaData.getColumnTypeName(column));
				System.out.println("Is Nullable: " + resultSetMetaData.isNullable(column));
				System.out.println("Is Auto Increment: " + resultSetMetaData.isAutoIncrement(column) + "\n");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(myConn, myStmt,myRs);
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

}
