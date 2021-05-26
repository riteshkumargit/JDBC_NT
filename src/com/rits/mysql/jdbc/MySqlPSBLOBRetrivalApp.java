package com.rits.mysql.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;


public class MySqlPSBLOBRetrivalApp {

	private static String SQLINSERTQUERY = "select * from artist_info where eid=?";
	
	public static void main(String[] args) {
		
		InputStream imageInputStream = null;
		InputStream videoInputStream = null;
		FileOutputStream imageFileOutputStream = null;
		FileOutputStream videoFileOutputStream = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Scanner scanner = null;
		
		int eid = 0;
		String ename = null;
		String eaddress = null;
		
		String url = "jdbc:mysql://localhost:3306/abc";
		String username = "root";
		String password = "root";
		
		try {
			scanner = new Scanner(System.in);
			
			System.out.println("Enter the employee id:: ");
			eid = scanner.nextInt();		
			
			connection = DriverManager.getConnection(url,username,password);
			if(connection != null)
			{
				preparedStatement =  connection.prepareStatement(SQLINSERTQUERY);
				if(preparedStatement != null)
				{
					preparedStatement.setInt(1,eid);
					
					resultSet = preparedStatement.executeQuery();
					
					if(resultSet != null)
					{
						if(resultSet.next() != false)
						{
							eid = resultSet.getInt(1);
							ename = resultSet.getString(2);
							eaddress = resultSet.getNString(3);
							
							imageInputStream = resultSet.getBinaryStream(4);
							videoInputStream = resultSet.getBinaryStream(5);
							
							String imagePath = "D://MyPic.png";
							String videoPath = "D://v1.mp4";
							
							imageFileOutputStream = new FileOutputStream(imagePath);
							videoFileOutputStream = new FileOutputStream(videoPath);
							
							if(imageInputStream != null)
							{
								int data = imageInputStream.read();
								while(data != -1)
								{
									imageFileOutputStream.write(data);
									data = imageInputStream.read();		
								}
								
							}
						}	
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
				if (imageFileOutputStream != null) {
					imageFileOutputStream.close();
				}

			} catch (Exception sqlException) {
				System.out.println("The cause of the exception is ::" + sqlException.getMessage());
			}
			try {
				if (videoFileOutputStream != null) {
					videoFileOutputStream.close();
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