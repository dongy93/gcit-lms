package com.gcit.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

//https://github.com/dongy93/gcit-lms.git
public class LibManage {
	public static final Scanner scan = new Scanner(System.in);
	private static String inputCheck;
	private static boolean valid;
	private static String createQuery;
	//Main Menu
	public static void mainMenu() {
		valid = false;
		System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you?");
		System.out.println();
		System.out.println("   1) Librarian");
		System.out.println("   2) Administrator");
		System.out.println("   3) Borrower");
		System.out.println("   0) Quit");
		while(valid == false) { 
			inputCheck = scan.next();
			if(inputCheck.equals("1")) {
				valid = true;
				System.out.println();
				librarian();
			}
			else if(inputCheck.equals("2")) {
				valid = true;
				System.out.println();
				admin();
			}
			else if(inputCheck.equals("3")) {
				valid = true;
				System.out.println();
				borrower();
			}
			else if(inputCheck.equals("0")) {
				valid = true;
				System.out.println("Thank you for using the GCIT manager. The program will now close.");
				System.exit(0);
			}
			else {
				System.out.println("That is not a valid option, please enter 1, 2, or 3. Press 0 to quit.");
			}
		}
	}
	//Librarian's Menu
	public static void librarian() {
		valid = false;
		System.out.println("1) Enter the branch you manage");
		System.out.println("2) Quit to the previous menu");
		System.out.println("0) Quit the system");
		while(valid == false) {
			inputCheck = scan.next();
			if(inputCheck.equals("1")) {
				valid = true;
				libranch();
			}
			else if(inputCheck.equals("2")) {
				valid = true;
				mainMenu();
			}
			else if(inputCheck.equals("0")) {
				valid = true;
				System.out.println("Thank you for using the GCIT manager. The program will now close.");
				System.exit(0);
			}
			else {
				System.out.println("That is not a valid option, please enter 1 or 2. Press 0 to quit.");
			}
		}
		
	}
	//Librarian List of Branches
	public static void libranch() {
		
	}
	//Administrator's Menu
	public static void admin() {
		
	}
	//Borrower's Menu
	public static void borrower() {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "TmRmfmqekak88");
			Statement stmt = conn.createStatement();
			mainMenu();
			//String createQuery = "insert into tbl_author (authorName) values('" +authorName+"')";
			
			//stmt.executeUpdate(createQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
