package com.gcit.training;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

//https://github.com/dongy93/gcit-lms.git
public class LibManage {
	public static final Scanner scan = new Scanner(System.in);
	private static String inputCheck;
	private static int idCheck;
	private static boolean valid;
	private static String displayQuery;
	private static String createQuery;
	private static String selectQuery;
	private static String updateQuery;
	private static String updateQuery2;
	private static Statement stmt;
	private static Connection conn;
	//Main Menu
	public static void mainMenu() {
		valid = false;
		System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you?");
		System.out.println();
		System.out.println("   1) Librarian");
		System.out.println("   2) Administrator");
		System.out.println("   3) Borrower");
		System.out.println("   0) Quit");
		//Check if input is valid
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
			//Program Quit
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
		//Check for valid input
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
			//Program Quit
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
		valid = false;
		System.out.println("Which branch do you work in? Input the ID number");
		System.out.println();
		/*int count;*/
		try {
			//Display Entire Library Branch Table in database
			displayQuery = "select * from tbl_library_branch";
			PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
			ResultSet rsDisp = dispstmt.executeQuery();
			System.out.println("ID	Branch Name");
			while(rsDisp.next()) {
				int id = rsDisp.getInt("branchId");
				String brName = rsDisp.getString("branchName");
				String brAddress = rsDisp.getString("branchAddress");
				System.out.println(id + ")	" + brName + ", " + brAddress);
			}
			//Branch ID input
			idCheck = scan.nextInt();
			branchMenu(idCheck);
			/*
			selectQuery = "select * from tbl_library_branch where branchId= ?";
			PreparedStatement selstmt = conn.prepareStatement(selectQuery);
			selstmt.setInt(1, idCheck);
			ResultSet rsSel = selstmt.executeQuery();
			while(rsSel.next()) {
			*/
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void branchMenu(int branchNum) {
		valid = false;
		System.out.println("Type the number for what you want to do next.");
		System.out.println();
		System.out.println("1) Update the details of the Library");
		System.out.println("2) Add Copies of a book to the branch");
		System.out.println("3) Quit to previous");
		System.out.println("0) Quit the System");
		while(valid == false) {
			inputCheck = scan.next();
			//Library Branch Update option for Librarian
			if(inputCheck.equals("1")) {
				valid = true;
				libUpdate();
			}
			//Back to Main Menu
			else if(inputCheck.equals("2")) {
				valid = true;
				libAddBook(idCheck);
			}
			//Back to Branch Selection
			else if(inputCheck.equals("3")) {
				valid = true;
				libranch();
			}
			//Program Quit
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
	public static void libUpdate() {
		try {
			//Update Prepared Statement, to input 1. What to change 2. What to change it to 3. Branch ID
			updateQuery = "update tbl_library_branch set branchName=? where branchId= ?";
			PreparedStatement upstmt = conn.prepareStatement(updateQuery);
			updateQuery2 = "update tbl_library_branch set branchAddress=? where branchId= ?";
			PreparedStatement upstmt2 = conn.prepareStatement(updateQuery2);
			//upstmt.executeUpdate();
			//Select Prepared Statement, to input Branch ID
			selectQuery = "select * from tbl_library_branch where branchId= ?";
			PreparedStatement selstmt = conn.prepareStatement(selectQuery);
			selstmt.setInt(1, idCheck);
			ResultSet rsSel = selstmt.executeQuery();
			while(rsSel.next()) {
				System.out.println("You have chosen to update the Branch with Branch Id: " + idCheck + " and Branch Name: " + rsSel.getString("branchName"));
				System.out.println("Enter 'quit' at any prompt to cancel operation.");
				System.out.println();
				System.out.println("Please enter new branch name or enter N/A for no change");
			}
				rsSel.close();
				inputCheck = scan.next();
			
				//Program Quit
				if(inputCheck.equals("quit")) {
					System.out.println("Thank you for using the GCIT manager. The program will now close.");
					System.exit(0);
				}
				//Change nothing if N/A is input
				else if(inputCheck.equals("N/A")) {
				}
				//Change Branch name to Librarian's input
				else {
					upstmt.setString(1, inputCheck);
					upstmt.setInt(2, idCheck);
					//stmt.executeUpdate(updateQuery);
					upstmt.executeUpdate();
				}
				System.out.println("Please enter new branch address or enter N/A for no change");
				inputCheck = scan.next();
				//Program Quit
				if(inputCheck.equals("quit")) {
					System.out.println("Thank you for using the GCIT manager. The program will now close.");
					System.exit(0);
				}
				//Change nothing if N/A is input
				else if(inputCheck.equals("N/A")) {
					System.out.println("Successfully updated. You will now be taken to the librarian's menu for your branch.");
					branchMenu(idCheck);
				}
				//Change Branch address to Librarian's input
				else {
					upstmt2.setString(1, inputCheck);
					upstmt2.setInt(2, idCheck);
					//stmt.executeUpdate(updateQuery);
					upstmt2.executeUpdate();
					System.out.println("Successfully updated. You will now be taken to the librarian's menu for your branch.");
					branchMenu(idCheck);
					
				}

			
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void libAddBook(int branchNum) {
		System.out.println("Pick the book you want to add copies of, to your branch: ");
		try {
			displayQuery = "select * from tbl_book_authors join tbl_book on "
			+ "tbl_book.bookId=tbl_book_authors.bookId join tbl_author on "
			+ "tbl_author.authorId=tbl_book_authors.authorId order by tbl_book.bookId";
			PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
			ResultSet rsDisp = dispstmt.executeQuery();
			System.out.println("ID	Book Name");
			while(rsDisp.next()) {
				int id = rsDisp.getInt("bookId");
				String title = rsDisp.getString("title");
				String authName = rsDisp.getString("authorName");
				System.out.println(id + ")	" + title + " by " + authName);
			}
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "TmRmfmqekak88");
			stmt = conn.createStatement();
			
			mainMenu();
			//String createQuery = "insert into tbl_author (authorName) values('" +authorName+"')";
			
			//stmt.executeUpdate(createQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
