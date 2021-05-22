package com.rits.oracle.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatementOracleInjectionApp {

	private static final String SQLSELECTQUERY = "select count(*) from login where username=? and password=?";
	
	public static void main(String[] args)
	{
		FileInputStream fileInputStream = null;
		Properties properties = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Scanner scan = null;
	
		String uname = null;
		String pwd = null;
	
		String fileName = "D:\\ABC\\AdvanceJava\\Jdbc1\\src\\com\\rits\\oracle\\jdbc\\resource\\"+args[0];
		System.out.println(fileName);
		
		try {
			scan =new Scanner(System.in);
			
			System.out.println("Enter the username::");
			uname =scan.next();
			
			System.out.println("Enter the password::");
			pwd =scan.next();
			
			fileInputStream = new FileInputStream(fileName);
			properties = new Properties();
			properties.load(fileInputStream);
			
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			
			System.out.println("The url is: "+url);
			System.out.println("The username is: "+username);
			System.out.println("The password is: "+password);
			
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null) {
				
				// Step2.Creating the statement object to sent the query to DB
				preparedStatement = connection.prepareStatement(SQLSELECTQUERY);

				if (preparedStatement != null) {

					// Setting the values dynamically through setXXX()
					preparedStatement.setString(1, uname);
					preparedStatement.setString(2, pwd);

					System.out.println("Query executed by DBE is :: " + SQLSELECTQUERY);

					// Step3.Execute the query by sending to DBE
					resultSet = preparedStatement.executeQuery();

					// Step4.Processing the resultSet object
					if (resultSet.next() != false) {
						
						int rowCount = resultSet.getInt(1);
						if (rowCount == 0) {
							System.out.println("Login credentails are incorrect");
						} else {
							System.out.println("Login succesfull!!!");
						}

					}

				}

			}
		}catch (FileNotFoundException e) {
			System.out.println("The cause of the exception is ::" + e.getMessage());
		}catch (IOException e) {
			System.out.println("The cause of the exception is ::" + e.getMessage());
		}catch (Exception e) {
			System.out.println("The cause of the exception is ::" + e.getMessage());
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}

			} catch (Exception sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}
			try {
				if (preparedStatement != null) {
					preparedStatement .close();
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