package com.rits.mysql.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PreparedStatementApp {
	
	private static String query = "insert into student values(18,'Kohli','RCB')";

	public static void main(String[] args) {
		
		FileInputStream fileInputStream =null;
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		
		String fileName = "D:\\ABC\\AdvanceJava\\Jdbc1\\src\\com\\rits\\mysql\\jdbc\\resource\\"+args[0];
		System.out.println(fileName);
		
		try {
			fileInputStream = new FileInputStream(fileName);
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			
			System.out.println("The url is: "+url);
			System.out.println("The username is: "+username);
			System.out.println("The password is: "+password);
			
			connection = DriverManager.getConnection(url,username,password);
			if(connection != null)
			{
				System.out.println("Connection estaiblished with DB successfully!!!!");
				preparedStatement = connection.prepareStatement(query);
				if(preparedStatement != null)
				{
					int rowAffected = preparedStatement.executeUpdate();
					
					if(rowAffected ==0)
					{
						System.out.println("Insertion Failed");
					}else {
						System.out.println("Row inserted successfully!!!!");
					}
				}
			}
			
		} catch (FileNotFoundException e) {
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