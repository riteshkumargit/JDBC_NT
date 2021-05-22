package com.rits.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainAppMySqlDbUpdateOperation {

	public static void main(String[] args) {

		// Resources used in the application
		Connection connection = null;
		Statement statement = null;
		Scanner scan = null;
		
		String url = "jdbc:mysql://localhost:3306/abc";
		String user = "root";
		String password = "root";
		
		int sid = 0;

		try {
			scan = new Scanner(System.in);
			if(scan != null)
			{
				System.out.println("Enter the student id: ");
				sid = scan.nextInt();
				
			}
			
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				System.out.println("Connected to database succesfully!!!!");

				statement = connection.createStatement();

				if (statement != null) {

					String sqlQuery = "delete from student where sid = " + sid;
					System.out.println(sqlQuery);


					int count = statement.executeUpdate(sqlQuery);
					if(count != -1)
					System.out.println(" Record Deleted Successfully::" +count);
				}else {
					System.out.println("No Record Deleted");
				}

			}

		} catch (SQLException e) {
			System.out.println("The cause of the exception is ::" + e.getMessage());
		} finally {
			
			try {
				if (statement != null) {
					statement.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}

		}

	}

}