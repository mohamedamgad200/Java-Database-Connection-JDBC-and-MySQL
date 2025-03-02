package com.jdbctest;

import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.sql.*;

public class ConnectionDemo {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			// 1. Load the properties file
			Properties prop = new Properties();
			prop.load(new FileInputStream("demo.properties"));
			// 2. Read the props
			String theDburl = prop.getProperty("dburl");
			String theUser = prop.getProperty("user");
			String thePassword = prop.getProperty("password");

			System.out.println("Connecting to database...");
			System.out.println("Database URL: " + theDburl);
			System.out.println("User: " + theUser);
			System.out.println("Password: " + thePassword);

			// 3. Get a connection to database
			myConn = DriverManager.getConnection(theDburl, theUser, thePassword);
			System.out.println("\nConnection successful!\n");

			// 4. Create a statement
			myStmt = myConn.createStatement();

			// 5. Execute SQL query
			myRs = myStmt.executeQuery("select * from employees");

			// 6. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
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
