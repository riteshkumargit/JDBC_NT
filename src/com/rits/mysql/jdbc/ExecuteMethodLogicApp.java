package com.rits.mysql.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ExecuteMethodLogicApp {

	public static void main(String[] args) {
		
		FileInputStream fileInputStream =null;
		Connection connection = null;
		Statement statement =null;
		ResultSet resultSet = null;
		
		String fileName = "D:\\ABC\\AdvanceJava\\Jdbc1\\src\\com\\rits\\mysql\\jdbc\\resource\\"+args[0];
		System.out.println(fileName);
		
		try {
			fileInputStream = new FileInputStream(fileName);
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");

			System.out.println("The url is : " + url);
			System.out.println("The username is : " + username);
			System.out.println("The password is : " + password);

			// Step1.Establish the connection b/w java app and database
			connection = DriverManager.getConnection(url, username, password);
			
			if(connection != null)
			{
				statement = connection.createStatement();
				if(statement !=null)
				{
					String query = "select * from student where sid = 19";
					boolean flag = statement.execute(query);
					
					if(flag==true)
					{
						System.out.println("Select Query is Executing...");
						resultSet = statement.getResultSet();
						if(resultSet != null)
						{
							System.out.println("Sid \t\t\t Sname \t\t\t\t Saddress");
							while(resultSet.next() != false)
							{
								System.out.println(resultSet.getInt(1)+ "\t\t\t"+ resultSet.getString(2)+" \t\t\t\t"+resultSet.getString(3));
							}
						}
					}else {
						System.out.println("DML query is executing");
						int rowAffected = statement.getUpdateCount();
						if(rowAffected==0)
						{
							System.out.println("Record not available for deletion");
						}else {
							System.out.println("Record got deleted successfully...");
						}
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