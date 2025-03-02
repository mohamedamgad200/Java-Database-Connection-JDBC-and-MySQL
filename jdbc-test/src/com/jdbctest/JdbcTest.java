package com.jdbctest;

import java.sql.*;

public class JdbcTest {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStat = null;
		ResultSet myRes = null;
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(dbUrl, user, pass);
			System.out.println("Database connected successful! \n");

			// 2. Create a statement
			myStat = myConn.createStatement();

			// 3. Execute SQL query
			myRes = myStat.executeQuery("select * from employees");

			// 4. Process the result set
			while (myRes.next()) {
				System.out.println(myRes.getString("last_name") + " , " + myRes.getString("first_name"));
			}
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
}
