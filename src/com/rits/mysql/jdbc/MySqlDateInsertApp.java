package com.rits.mysql.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class MySqlDateInsertApp {

	private static String SQLINSERTQUERY = "insert into employee values(?'?'?'?'?'?)";
	
	public static void main(String[] args) {
		
		FileInputStream fileInputStream = null;
		Properties properties = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = null;
		SimpleDateFormat sdf1 = null, sdf2 = null;
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
			
			System.out.println("Enter the employee id:: ");
			eid = scanner.nextInt();
			
			System.out.println("Enter the employee name:: ");
			ename = scanner.next();
			
			System.out.println("Enter the employee dob(DD-MM-YYYY):: ");
			edob = scanner.next();
			
			System.out.println("Enter the employee dom(MM-DD-YYYY):: ");
			edom = scanner.next();
			
			System.out.println("Enter the employee doj(YYYY-MM-DD):: ");
			edoj = scanner.next();
			
			System.out.println("Enter the employee address:: ");
			eaddress = scanner.next();
			
			fileInputStream = new FileInputStream(fileName);
			properties = new Properties();
			properties.load(fileInputStream);
			
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
			
			sdf1 = new SimpleDateFormat("dd-mm-yyyy");
			
			if(sdf1 != null)
			{
				udob = sdf1.parse(edob);
				if(udob != null)
				{
					sqlDob = new java.sql.Date(udob.getTime());
				}
			}
			
			sdf2 = new SimpleDateFormat("MM-DD-YYYY");
			if(sdf2 != null)
			{
				udom = sdf2.parse(edom);
				if(udom != null)
				{
					sqlDom = new java.sql.Date(udom.getTime());
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