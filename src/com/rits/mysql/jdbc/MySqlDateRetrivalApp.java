package com.rits.mysql.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class MySqlDateRetrivalApp {

	private static String SQLINSERTQUERY = "select * from employee where eid=?";
	
	public static void main(String[] args) {
		
		FileInputStream fileInputStream = null;
		Properties properties = null;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = null;
		SimpleDateFormat sdf = null;
		Date udob = null, udom = null;
		java.sql.Date sqlDob = null, sqlDom = null, sqlDoj = null;
		
		int eid = 0;
		String ename = null;
		String edob = null;
		String edom = null;
		String edoj = null;
		String eaddress = null;
		
		String fileName = "D:\\ABC\\AdvanceJava\\Jdbc1\\src\\com\\rits\\mysql\\jdbc\\resource\\" + args[0];
		System.out.println(fileName);
		
		try {
			
			scanner = new Scanner(System.in);
			System.out.println("Enter the id to get record:: ");
			eid = scanner.nextInt();
			
			fileInputStream = new FileInputStream(fileName);
			properties = new Properties();
			properties.load(fileInputStream);
			
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
			
			connection = DriverManager.getConnection(url,username,password);
			if(connection != null)
			{
				preparedStatement =  connection.prepareStatement(SQLINSERTQUERY);
				if(preparedStatement != null)
				{
					preparedStatement.setInt(1, eid);
					resultSet = preparedStatement.executeQuery();
					if(resultSet != null)
					{
						if(resultSet.next() != false)
						{
							eid = resultSet.getInt(1);
							ename = resultSet.getString(2);
							sqlDob = resultSet.getDate(3);
							sqlDom = resultSet.getDate(4);
							sqlDoj = resultSet.getDate(5);
							eaddress = resultSet.getString(6);
							
							sdf = new SimpleDateFormat("YYYY-MM-DD");
							edob = sdf.format(sqlDob);
							edom = sdf.format(sqlDom);
							edoj = sdf.format(sqlDoj);
							
							System.out.println("------------------------------------------------");
							System.out.println("Eid:: "+eid+"\t Ename:: "+ename+"\t Edob:: "+edob+"\t Edom:: "+edom+"\t Edoj:: "+edoj+"\t Eaddress:: "+eaddress);
						}else {
							System.out.println("Record is not available for given id");
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