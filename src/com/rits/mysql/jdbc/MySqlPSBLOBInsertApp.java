package com.rits.mysql.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class MySqlPSBLOBInsertApp {

	private static String SQLINSERTQUERY = "insert into artist_info values(?,?,?,?,?)";
	
	public static void main(String[] args) {
		
		FileInputStream imageFileInputStream = null;
		FileInputStream videoFileInputStream = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner scanner = null;
		
		int eid = 0;
		String ename = null;
		String eaddress = null;
		String imagePath = null;
		String videoPath = null;
		
		String url = "jdbc:mysql://localhost:3306/abc";
		String username = "root";
		String password = "root";
		
		try {
			scanner = new Scanner(System.in);
			
			System.out.println("Enter the employee id:: ");
			eid = scanner.nextInt();
			
			System.out.println("Enter the employee name:: ");
			ename = scanner.next();
			
			System.out.println("Enter the employee address:: ");
			eaddress = scanner.next();
			
			System.out.println("Enter the employee image:: ");
			imagePath = scanner.next();
			
			System.out.println("Enter the employee video:: ");
			videoPath = scanner.next();
			
			
			
			imageFileInputStream = new FileInputStream(imagePath);
			videoFileInputStream = new FileInputStream(videoPath);
			
			connection = DriverManager.getConnection(url,username,password);
			if(connection != null)
			{
				preparedStatement =  connection.prepareStatement(SQLINSERTQUERY);
				if(preparedStatement != null)
				{
					preparedStatement.setInt(1,eid);
					preparedStatement.setString(2,ename);
					preparedStatement.setString(3,eaddress);
					preparedStatement.setBinaryStream(4, imageFileInputStream);
					preparedStatement.setBinaryStream(5, videoFileInputStream);
					
					int rowAffected = preparedStatement.executeUpdate();
					
					if(rowAffected == 0)
					{
						System.out.println("Not Inserted Data successfully!!!!");
					}else {
						System.out.println("Inserted Data successfully!!!!");
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
				if (imageFileInputStream != null) {
					imageFileInputStream.close();
				}

			} catch (Exception sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}
			try {
				if (videoFileInputStream != null) {
					videoFileInputStream.close();
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