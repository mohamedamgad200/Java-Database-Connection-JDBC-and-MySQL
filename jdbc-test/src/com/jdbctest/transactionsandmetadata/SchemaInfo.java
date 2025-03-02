package com.jdbctest.transactionsandmetadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.catalog.Catalog;

public class SchemaInfo {
	public static void main(String[] args)throws SQLException {
		Connection myConn = null;
		ResultSet myRs = null;
		String catalog = null;
		String columnNamePattern = null;
		String tableNamePattern = null;
		String schemaPattern = null;
		String[] types = null;
		String URL = "jdbc:mysql://localhost:3306/demo";
		String USER = "student";
		String PASS = "student";
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(URL, USER, PASS);
			// 2. Get metadata
			DatabaseMetaData databaseMetaData = myConn.getMetaData();
			// 3. Get list of tables
			System.out.println("List of Tables");
			System.out.println("--------------");
			myRs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			while (myRs.next()) {
				System.out.println(myRs.getString("TABLE_NAME"));
			}
			// 4. Get list of columns
			System.out.println("\n\nList of Columns");
			System.out.println("--------------");
			myRs = databaseMetaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);

			while (myRs.next()) {
				System.out.println(myRs.getString("COLUMN_NAME"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(myConn, myRs);
		}

	}

	private static void close(Connection myConn, ResultSet myRs) throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

}
