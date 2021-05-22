package com.rits.oracle.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PropertiesFileApproachOracle {

	public static void main(String[] args) {
		
		
		FileInputStream fileInputStream = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String fileName = "D:\\ABC\\AdvanceJava\\Jdbc1\\src\\com\\rits\\oracle\\jdbc\\resource\\"+args[0];
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
				System.out.println("Connection estaiblished successfully!!!! ");
				statement = connection.createStatement();
				
				if(statement != null)
				{
					String query = "select * from mytable";
					resultSet = statement.executeQuery(query);
					
					if(resultSet != null)
					{
						System.out.println("Sid \t\t\t Sname \t\t\t\t Sage");
						if(resultSet.getFetchSize()>=0)
						{
							while(resultSet.next() != false)
							{
								System.out.println(resultSet.getInt(1)+" \t\t\t "+resultSet.getString(2)+" \t\t\t "+resultSet.getInt(3));
							}
						}
					}else {
						System.out.println("No Records available!!!!");
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
					resultSet.close();
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