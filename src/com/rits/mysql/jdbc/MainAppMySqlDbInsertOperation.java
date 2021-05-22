package com.rits.mysql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MainAppMySqlDbInsertOperation {

	public static void main(String[] args) {

		// Resources used in the application
		Connection connection = null;
		Statement statement = null;
		Scanner scan = null;
		
		String url = "jdbc:mysql://localhost:3306/abc";
		String user = "root";
		String password = "root";
		
		int sid = 0;
		String sname = null;
		String saddress = null;

		try {
			scan = new Scanner(System.in);
			if(scan != null)
			{
				System.out.println("Enter the student id: ");
				sid = scan.nextInt();
				
				System.out.println("Enter the student name: ");
				sname = scan.next();
				
				System.out.println("Enter the student address: ");
				saddress= scan.next();
			}
			// Step1.Establishing the connection b/w java application and database(MySQL)
			connection = DriverManager.getConnection(url, user, password);

			if (connection != null) {
				System.out.println("Connected to database succesfully!!!!");

				// Step2.Create an Statement Object as per the user needs.
				statement = connection.createStatement();

				if (statement != null) {

					String sqlQuery = "insert into student (Sid,Sname,Saddress) values(" + sid + ",'" + sname + "','"+ saddress + "');";
					System.out.println(sqlQuery);
					// Step3.Execute the query as per the requirement
					int count = statement.executeUpdate(sqlQuery);
					if(count != -1)
					System.out.println("Number of Record inserted Successfully::" +count);
				}else {
					System.out.println("No Record inserted");
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