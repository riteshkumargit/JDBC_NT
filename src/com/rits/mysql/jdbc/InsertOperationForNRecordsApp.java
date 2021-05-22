package com.rits.mysql.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class InsertOperationForNRecordsApp {
	
	private static String DYNAMICQUERY = "insert into student values(?,?,?)";
	
	public static void main(String[] args) {
		
		FileInputStream fileInputStream = null;
		Properties properties = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scan =null;
		
		int count = 0;
		int sid = 0;
		String sname = null;
		String saddress =null;
		
		String fileName = "D:\\ABC\\AdvanceJava\\Jdbc1\\src\\com\\rits\\mysql\\jdbc\\resource\\" + args[0];
		System.out.println(fileName);
		try {
			scan = new Scanner(System.in);
			if(scan != null)
			{	
				System.out.println("Enter the number of records that is to be insert:: ");
				count = scan.nextInt();
			}
			
			fileInputStream = new FileInputStream(fileName);
			properties = new Properties();
			properties.load(fileInputStream);
			
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			
			System.out.println("The url is: "+url);
			System.out.println("The username is: "+username);
			System.out.println("The password is: "+password);
			
			connection = DriverManager.getConnection(url,username,password);
			if (connection != null) {

				// Step2.Create the PreparedStatement object
				preparedStatement = connection.prepareStatement(DYNAMICQUERY);

				if (preparedStatement != null) {

					for (int i = 1; i <= count; i++) {
						
						if (scan != null) {

							System.out.println("Enter the id: ");
							sid = scan.nextInt();

							System.out.println("Enter the name:: ");
							sname = scan.next();

							System.out.println("Enter the address:: ");
							saddress = scan.next();

							preparedStatement.setInt(1, sid);
							preparedStatement.setString(2, sname);
							preparedStatement.setString(3, saddress);

						}
						
					}//end of for loop
					// Step4.Executing the PreparedStatement object
					int rowAffected = preparedStatement.executeUpdate();
					
					
					if (rowAffected == 0) {
						System.out.println("Insertion failed");
					} else {
						System.out.println(count+" Row inserted Succesfully!!!!");
					}

				}//end of if

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
				if (preparedStatement != null) {
					preparedStatement.close();
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