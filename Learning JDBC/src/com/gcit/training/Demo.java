package com.gcit.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Demo {

	public static void main(String[] args) {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "TmRmfmqekak88");
			Statement stmt = conn.createStatement();
			String selectQuery = "select * from tbl_author where authorName= ?";
			
			PreparedStatement pstmt = conn.prepareStatement(selectQuery);
			pstmt.setString(1, "Author");
			
			
			ResultSet rs = pstmt.executeQuery(); 
					//stmt.executeQuery(selectQuery);
			
			while(rs.next()){
				System.out.println("Author ID: " +rs.getInt("authorId"));
				System.out.println("Author Name: " +rs.getString("authorName"));
				System.out.println("-----------------------------");
			}
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter a new Author: ");
			String authorName = scan.nextLine();
			
			String createQuery = "insert into tbl_author (authorName) values('" +authorName+"')";
			
			stmt.executeUpdate(createQuery);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
