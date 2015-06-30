package com.gcit.training;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//https://github.com/dongy93/gcit-lms.git
public class LibManage {
	public static final Scanner scan = new Scanner(System.in);
	private static String inputCheck;
	private static String adminUpdate;
	private static String adminUpdate2;
	private static String adminUpdate3;
	private static int idCheck;
	private static int idCheck2;
	private static int cardNum;
	private static int countIndex;
	private static int bookCount;
	private static int addBook;
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
				inputCheck = scan.nextLine();
				inputCheck = scan.nextLine();
			
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
				inputCheck = scan.nextLine();
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
			e.printStackTrace();
		}
	}
	//TODO 
	public static void libAddBook(int branchNum) {
		System.out.println("Pick the book you want to add copies of, to your branch: ");
		try {
			countIndex=0;
			bookCount=0;
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
				countIndex++;
			}
			idCheck = scan.nextInt();
			if(idCheck < 1 || idCheck > countIndex) {
				System.out.println("That is not a valid index for a book");
				libAddBook(branchNum);
			}
			displayQuery = "select noOfCopies from tbl_book_copies where bookId=? and branchId=?";
			PreparedStatement dispstmt2 = conn.prepareStatement(displayQuery);
			dispstmt2.setInt(1, idCheck);
			dispstmt2.setInt(2, branchNum);
			ResultSet rsDisp2 = dispstmt2.executeQuery();
			//If there is no book of the title in noOfCopies print out 0
			//Then add new row with noOfCopies
			if(!rsDisp2.isBeforeFirst()) {
				bookCount = 0;
				System.out.println("Existing number of copies: 0");
				System.out.println("Enter new number of copies: ");
				addBook = scan.nextInt();
				bookCount = bookCount + addBook;
				createQuery = "insert into tbl_book_copies values (?, ?, ?)";
				PreparedStatement crestmt = conn.prepareStatement(createQuery);
				crestmt.setInt(1, idCheck);
				crestmt.setInt(2, branchNum);
				crestmt.setInt(3, bookCount);
				crestmt.executeUpdate();
				System.out.println("Copies of book added.");
			}
			//Same as when there were no books except updating existing table elements rather than creating
			while(rsDisp2.next()) {
				bookCount = rsDisp2.getInt("noOfCopies");
				System.out.println("Existing number of copies: " + bookCount);
				System.out.println("Enter new number of copies: ");
				addBook = scan.nextInt();
				bookCount = bookCount + addBook;
				updateQuery = "update tbl_book_copies set noOfCopies=? where bookId=? and branchId=?";
				PreparedStatement upstmt = conn.prepareStatement(updateQuery);
				upstmt.setInt(1, bookCount);
				upstmt.setInt(2, idCheck);
				upstmt.setInt(3, branchNum);
				upstmt.executeUpdate();
				System.out.println("Copies of book added.");
			}
			rsDisp.close();
			rsDisp2.close();
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Administrator's Menu
	public static void admin() {
		valid = false;
		System.out.println("Select what you would like to do.");
		System.out.println("1) Add/Update/Delete Book and Author");
		System.out.println("2) Add/Update/Delete Publishers");
		System.out.println("3) Add/Update/Delete Library Branches");
		System.out.println("4) Add/Update/Delete Borrowers");
		System.out.println("5) Over-ride Due Date for a Book Loan");
		while(valid == false) { 
			inputCheck = scan.next();
			if(inputCheck.equals("1")) {
				valid = true;
				System.out.println();
				bookAdmin();
			}
			else if(inputCheck.equals("2")) {
				valid = true;
				System.out.println();
				publishAdmin();
			}
			else if(inputCheck.equals("3")) {
				valid = true;
				System.out.println();
				libranchAdmin();
			}
			else if(inputCheck.equals("4")) {
				valid = true;
				System.out.println();
				borrowerAdmin();
			}
			else if(inputCheck.equals("5")) {
				valid = true;
				System.out.println();
				adminOverride();
			}
			//Program Quit
			else if(inputCheck.equals("0")) {
				valid = true;
				System.out.println("Thank you for using the GCIT manager. The program will now close.");
				System.exit(0);
			}
			else {
				System.out.println("That is not a valid option, please enter 1, 2, 3, 4, or 5. Press 0 to quit.");
			}
		}
	}
	public static void bookAdmin() {
		valid = false;
		try{	
			System.out.println("You are in the Authors table. What would you like to do?");
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("0) Quit");
			while(valid == false) {
				inputCheck = scan.next();
				if(inputCheck.equals("1")) {
					System.out.println("Input Author Name: ");
					adminUpdate = scan.nextLine();
					adminUpdate = scan.nextLine();
					updateQuery = "insert into tbl_author (authorName) values (?);";
					PreparedStatement upstmt = conn.prepareStatement(updateQuery);
					upstmt.setString(1, adminUpdate);
					upstmt.executeUpdate();
					System.out.println("New Author added.");
					admin();
				}
				else if(inputCheck.equals("2")) {
					System.out.println("Select the Author to update: ");
					displayQuery = "select * from tbl_author";
					PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
					ResultSet rsDisp = dispstmt.executeQuery();
					System.out.println("ID	Author Name");
					while(rsDisp.next()) {
						int id = rsDisp.getInt("authorId");
						String name = rsDisp.getString("authorName");
						System.out.println(id + ")	" + name);
					}
					idCheck = scan.nextInt();
					updateQuery2 = "update tbl_author set authorName=? where authorId=?";
					PreparedStatement upstmt2 = conn.prepareStatement(updateQuery2);
					selectQuery = "select * from tbl_author where authorId= ?";
					PreparedStatement selstmt = conn.prepareStatement(selectQuery);
					selstmt.setInt(1, idCheck);
					ResultSet rsSel = selstmt.executeQuery();
					while(rsSel.next()) {
						System.out.println("You have chosen to update the Author with ID number: " + idCheck + " and Name: " + rsSel.getString("authorName"));
						System.out.println("Enter 'quit' at any prompt to cancel operation.");
						System.out.println();
						System.out.println("Please enter new Author name or enter N/A for no change");
					}
					rsSel.close();
					inputCheck = scan.nextLine();
					inputCheck = scan.nextLine();
					
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
						//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
					}
						//Change Borrower name to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
						System.out.println("Author update successful.");
						admin();
					}
				}
				else if(inputCheck.equals("3")) {
					System.out.println("Select the author to delete: ");
					displayQuery = "select * from tbl_author";
					PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
					ResultSet rsDisp = dispstmt.executeQuery();
					System.out.println("ID	Name");
					while(rsDisp.next()) {
						int id = rsDisp.getInt("authorId");
						String name = rsDisp.getString("authorName");
						System.out.println(id + ")	" + name);
					}
					idCheck = scan.nextInt();
					createQuery = "delete from tbl_author where authorId=?";
					PreparedStatement crestmt = conn.prepareStatement(createQuery);
					crestmt.setInt(1, idCheck);
					crestmt.executeUpdate();
					System.out.println("Delete successful");
					admin();
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
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public static void publishAdmin() {
		valid = false;
		try {	
			System.out.println("You are in the Publisher table. What would you like to do?");
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("0) Quit");
			while(valid == false) {
				inputCheck = scan.next();
				if(inputCheck.equals("1")) {
					System.out.println("Input Publisher Name: ");
					adminUpdate = scan.nextLine();
					adminUpdate = scan.nextLine();
					System.out.println("Input Publisher Address: ");
					adminUpdate2 = scan.nextLine();
					System.out.println("Input Publisher Phone Number: ");
					adminUpdate3 = scan.nextLine();
					updateQuery = "insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?);";
					PreparedStatement upstmt = conn.prepareStatement(updateQuery);
					upstmt.setString(1, adminUpdate);
					upstmt.setString(2, adminUpdate2);
					upstmt.setString(3, adminUpdate3);
					upstmt.executeUpdate();
					System.out.println("New Publisher added.");
					admin();
				}
				else if(inputCheck.equals("2")) {
					System.out.println("Select the publisher to update: ");
					displayQuery = "select * from tbl_publisher";
					PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
					ResultSet rsDisp = dispstmt.executeQuery();
					System.out.println("ID	Publisher Name	Publisher Address	Publisher Phone Number");
					while(rsDisp.next()) {
						int id = rsDisp.getInt("publisherId");
						String pubName = rsDisp.getString("publisherName");
						String pubAddress = rsDisp.getString("publisherAddress");
						String pubPhone = rsDisp.getString("publisherPhone");
						System.out.println(id + ")	" + pubName + "	" + pubAddress + "	" + pubPhone);
					}
					idCheck = scan.nextInt();
					updateQuery2 = "update tbl_publisher set publisherName=? where publisherId=?";
					PreparedStatement upstmt2 = conn.prepareStatement(updateQuery2);
					selectQuery = "select * from tbl_publisher where publisherId= ?";
					PreparedStatement selstmt = conn.prepareStatement(selectQuery);
					selstmt.setInt(1, idCheck);
					ResultSet rsSel = selstmt.executeQuery();
					while(rsSel.next()) {
						System.out.println("You have chosen to update the Publisher with Publisher Id: " + idCheck + " and Publisher Name: " + rsSel.getString("publisherName"));
						System.out.println("Enter 'quit' at any prompt to cancel operation.");
						System.out.println();
						System.out.println("Please enter new publisher name or enter N/A for no change");
					}
					rsSel.close();
					inputCheck = scan.nextLine();
					inputCheck = scan.nextLine();
					
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
						//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
					}
						//Change Publisher name to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
					}
					System.out.println("Please enter new publisher address or enter N/A for no change");
					inputCheck = scan.nextLine();
					updateQuery2 = "update tbl_publisher set publisherAddress=? where publisherId=?";
					upstmt2 = conn.prepareStatement(updateQuery2);
						
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
					//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
					}
					//Change Publisher address to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
					}
					System.out.println("Please enter new publisher phone number of enter N/A for no change");
					inputCheck = scan.nextLine();
					updateQuery2 = "update tbl_publisher set publisherPhone=? where publisherId=?";
					upstmt2 = conn.prepareStatement(updateQuery2);
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
					//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
						System.out.println("Publisher update successful");
						admin();
					}
					//Change Publisher Phone number to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
						System.out.println("Publisher update successful");
						admin();
					}
				}
				else if(inputCheck.equals("3")) {
					System.out.println("Select the publisher to delete: ");
					displayQuery = "select * from tbl_publisher";
					PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
					ResultSet rsDisp = dispstmt.executeQuery();
					System.out.println("ID	Publisher Name	Publisher Address	Publisher Phone Number");
					while(rsDisp.next()) {
						int id = rsDisp.getInt("publisherId");
						String pubName = rsDisp.getString("publisherName");
						String pubAddress = rsDisp.getString("publisherAddress");
						String pubPhone = rsDisp.getString("publisherPhone");
						System.out.println(id + ")	" + pubName + "	" + pubAddress + "	" + pubPhone);
					}
					idCheck = scan.nextInt();
					createQuery = "delete from tbl_publisher where publisherId=?";
					PreparedStatement crestmt = conn.prepareStatement(createQuery);
					crestmt.setInt(1, idCheck);
					crestmt.executeUpdate();
					System.out.println("Delete successful");
					admin();
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
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void libranchAdmin() {
		valid = false;
		try{	
			System.out.println("You are in the Library Branch table. What would you like to do?");
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("0) Quit");
			while(valid == false) {
				inputCheck = scan.next();
				if(inputCheck.equals("1")) {
					System.out.println("Input Branch Name: ");
					adminUpdate = scan.nextLine();
					adminUpdate = scan.nextLine();
					System.out.println("Input Branch Address: ");
					adminUpdate2 = scan.nextLine();
					updateQuery = "insert into tbl_library_branch (branchName, branchAddress) values (?, ?);";
					PreparedStatement upstmt = conn.prepareStatement(updateQuery);
					upstmt.setString(1, adminUpdate);
					upstmt.setString(2, adminUpdate2);
					upstmt.executeUpdate();
					System.out.println("New Branch added.");
					admin();
				}
				else if(inputCheck.equals("2")) {
					System.out.println("Select the Branch to update: ");
					displayQuery = "select * from tbl_library_branch";
					PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
					ResultSet rsDisp = dispstmt.executeQuery();
					System.out.println("ID	Branch Name	Branch Address");
					while(rsDisp.next()) {
						int id = rsDisp.getInt("branchId");
						String brName = rsDisp.getString("branchName");
						String brAddress = rsDisp.getString("branchAddress");
						System.out.println(id + ")	" + brName + "	" + brAddress);
					}
					idCheck = scan.nextInt();
					updateQuery2 = "update tbl_library_branch set branchName=? where branchId=?";
					PreparedStatement upstmt2 = conn.prepareStatement(updateQuery2);
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
					inputCheck = scan.nextLine();
					inputCheck = scan.nextLine();
					
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
						//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
					}
						//Change Branch name to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
					}
					System.out.println("Please enter new branch address or enter N/A for no change");
					inputCheck = scan.nextLine();
					updateQuery2 = "update tbl_library_branch set branchAddress=? where branchId=?";
					upstmt2 = conn.prepareStatement(updateQuery2);
						
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
					//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
						System.out.println("Branch update successful.");
						admin();
					}
					//Change Publisher address to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
						System.out.println("Branch update successful.");
						admin();
					}
				}
				else if(inputCheck.equals("3")) {
					System.out.println("Select the branch to delete: ");
					displayQuery = "select * from tbl_library_branch";
					PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
					ResultSet rsDisp = dispstmt.executeQuery();
					System.out.println("ID	Branch Name	Branch Address");
					while(rsDisp.next()) {
						int id = rsDisp.getInt("branchId");
						String brName = rsDisp.getString("branchName");
						String brAddress = rsDisp.getString("branchAddress");
						System.out.println(id + ")	" + brName + "	" + brAddress);
					}
					idCheck = scan.nextInt();
					createQuery = "delete from tbl_library_branch where branchId=?";
					PreparedStatement crestmt = conn.prepareStatement(createQuery);
					crestmt.setInt(1, idCheck);
					crestmt.executeUpdate();
					System.out.println("Delete successful");
					admin();
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
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void borrowerAdmin() {
		valid = false;
		try{	
			System.out.println("You are in the Borrowers table. What would you like to do?");
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) Delete");
			System.out.println("0) Quit");
			while(valid == false) {
				inputCheck = scan.next();
				if(inputCheck.equals("1")) {
					System.out.println("Input Borrower Name: ");
					adminUpdate = scan.nextLine();
					adminUpdate = scan.nextLine();
					System.out.println("Input Borrower Address: ");
					adminUpdate2 = scan.nextLine();
					System.out.println("Input Borrower Phone Number: ");
					adminUpdate3 = scan.nextLine();
					updateQuery = "insert into tbl_borrower (name, address, phone) values (?, ?, ?);";
					PreparedStatement upstmt = conn.prepareStatement(updateQuery);
					upstmt.setString(1, adminUpdate);
					upstmt.setString(2, adminUpdate2);
					upstmt.setString(3, adminUpdate3);
					upstmt.executeUpdate();
					System.out.println("New Borrower added.");
					admin();
				}
				else if(inputCheck.equals("2")) {
					System.out.println("Select the borrower to update: ");
					displayQuery = "select * from tbl_borrower";
					PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
					ResultSet rsDisp = dispstmt.executeQuery();
					System.out.println("ID	Borrower Name	Borrower Address	Borrower Phone Number");
					while(rsDisp.next()) {
						int id = rsDisp.getInt("cardNo");
						String name = rsDisp.getString("name");
						String address = rsDisp.getString("address");
						String phone = rsDisp.getString("phone");
						System.out.println(id + ")	" + name + "	" + address + "	" + phone);
					}
					idCheck = scan.nextInt();
					updateQuery2 = "update tbl_borrower set name=? where cardNo=?";
					PreparedStatement upstmt2 = conn.prepareStatement(updateQuery2);
					selectQuery = "select * from tbl_borrower where cardNo= ?";
					PreparedStatement selstmt = conn.prepareStatement(selectQuery);
					selstmt.setInt(1, idCheck);
					ResultSet rsSel = selstmt.executeQuery();
					while(rsSel.next()) {
						System.out.println("You have chosen to update the borrower with Card number: " + idCheck + " and Name: " + rsSel.getString("name"));
						System.out.println("Enter 'quit' at any prompt to cancel operation.");
						System.out.println();
						System.out.println("Please enter new borrower name or enter N/A for no change");
					}
					rsSel.close();
					inputCheck = scan.nextLine();
					inputCheck = scan.nextLine();
					
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
						//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
					}
						//Change Borrower name to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
					}
					System.out.println("Please enter new borrower address or enter N/A for no change");
					inputCheck = scan.nextLine();
					updateQuery2 = "update tbl_borrower set address=? where cardNo=?";
					upstmt2 = conn.prepareStatement(updateQuery2);
						
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
					//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
					}
					//Change borrower address to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
					}
					System.out.println("Please enter new borrower phone number of enter N/A for no change");
					inputCheck = scan.nextLine();
					updateQuery2 = "update tbl_borrower set phone=? where cardNo=?";
					upstmt2 = conn.prepareStatement(updateQuery2);
					//Program Quit
					if(inputCheck.equals("quit")) {
						System.out.println("Thank you for using the GCIT manager. The program will now close.");
						System.exit(0);
					}
					//Change nothing if N/A is input
					else if(inputCheck.equals("N/A")) {
						System.out.println("Borrower update successful");
						admin();
					}
					//Change Publisher Phone number to Admin's input
					else {
						upstmt2.setString(1, inputCheck);
						upstmt2.setInt(2, idCheck);
						upstmt2.executeUpdate();
						System.out.println("Borrower update successful");
						admin();
					}
				}
				else if(inputCheck.equals("3")) {
					System.out.println("Select the borrower to delete: ");
					displayQuery = "select * from tbl_borrower";
					PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
					ResultSet rsDisp = dispstmt.executeQuery();
					System.out.println("ID	Name	Address	Phone Number");
					while(rsDisp.next()) {
						int id = rsDisp.getInt("cardNo");
						String name = rsDisp.getString("name");
						String address = rsDisp.getString("address");
						String phone = rsDisp.getString("phone");
						System.out.println(id + ")	" + name + "	" + address + "	" + phone);
					}
					idCheck = scan.nextInt();
					createQuery = "delete from tbl_borrower where cardNo=?";
					PreparedStatement crestmt = conn.prepareStatement(createQuery);
					crestmt.setInt(1, idCheck);
					crestmt.executeUpdate();
					System.out.println("Delete successful");
					admin();
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
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void adminOverride() {
		try {
			DateFormat format1 = new SimpleDateFormat("YYYY-MM-dd");
			countIndex=0;
			displayQuery = "select * from tbl_book_loans";
			PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
			ResultSet rsDisp = dispstmt.executeQuery();
			System.out.println("loan number	book ID	branch ID	card number	dateOut	dueDate	dateIn");
			while(rsDisp.next()) {
				countIndex++;
				int bookID = rsDisp.getInt("bookId");
				int branchID = rsDisp.getInt("branchId");
				int cardNum = rsDisp.getInt("cardNo");
				String dateOut = new SimpleDateFormat("YYYY-MM-dd").format(rsDisp.getDate("dateOut"));
				String dueDate = new SimpleDateFormat("YYYY-MM-dd").format(rsDisp.getDate("dueDate"));
				String dateIn = new SimpleDateFormat("YYYY-MM-dd").format(rsDisp.getDate("dateIn"));
				System.out.println(countIndex + ")	" + bookID + " 	" + branchID + " 	" + cardNum + " 	" + dateOut + " 	" + dueDate + " 	" + dateIn);
			}
			System.out.println("Select the book ID of the book to override the due date");
			idCheck = scan.nextInt();
			System.out.println("Select the card Number of the borrower to override the due date");
			idCheck2 = scan.nextInt();
			System.out.println("Input the new due date in YYYY-MM-DD format");
			inputCheck = scan.nextLine();
			inputCheck = scan.nextLine();
			Date newDueDate = java.sql.Date.valueOf(inputCheck);
			updateQuery = "update tbl_book_loans set dueDate=? where bookId=? and cardNo=?";
			PreparedStatement upstmt = conn.prepareStatement(updateQuery);
			upstmt.setInt(2, idCheck);
			upstmt.setInt(3, idCheck2);
			upstmt.setDate(1, newDueDate);
			upstmt.executeUpdate();
			System.out.println("Update of due date Successful");
			admin();
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Borrower's Menu
	public static void borrower() {
		valid = false;
		System.out.println("Enter your card Number: ");
		try {
			idCheck = scan.nextInt();
			//Set cardNum to current id Check to save card number of user
			cardNum = idCheck;
			selectQuery = "select * from tbl_borrower where cardNo = ?";
			PreparedStatement selStmt = conn.prepareStatement(selectQuery);
			selStmt.setInt(1, idCheck);
			ResultSet rsSel = selStmt.executeQuery();
			if(!rsSel.isBeforeFirst()) {
				System.out.println("You are not registered as a borrower.");
				borrower();
			}
			else {
				System.out.println("Select what you want to do.");
				System.out.println("1) Check out a book");
				System.out.println("2) Return a book");
				System.out.println("3) Quit to previous menu");
				System.out.println("0) Quit");
				while(valid == false) {
					inputCheck = scan.next();
					//Library Branch Update option for Librarian
					if(inputCheck.equals("1")) {
						valid = true;
						bookCheckout(idCheck);
					}
					//Back to Main Menu
					else if(inputCheck.equals("2")) {
						valid = true;
						bookReturn(idCheck);
					}
					//Back to Branch Selection
					else if(inputCheck.equals("3")) {
						valid = true;
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
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void bookCheckout(int cardNumber) {
		valid = false;
		System.out.println("Pick the branch you want to check out from: ");
		System.out.println();
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
			System.out.println("0) Quit to Previous Menu");
			//Branch ID input
			idCheck = scan.nextInt();
			if(idCheck == 0) {
				borrower();
			}
			else {
				borrowBook(idCheck);
			}
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static void borrowBook(int branchNum) {
		System.out.println("Pick the book you want to check out");
		try {
			//Display table with book titles available in input library branch ID
			displayQuery = "select * from tbl_library_branch join tbl_book_copies on "
			+ "tbl_book_copies.branchId=tbl_library_branch.branchId join tbl_book_authors on "
			+ "tbl_book_authors.bookId=tbl_book_copies.bookId join tbl_book on "
			+ "tbl_book.bookId=tbl_book_authors.bookId join tbl_author on "
			+ "tbl_author.authorId=tbl_book_authors.authorId "
			+ "where tbl_book_copies.branchId=? order by tbl_book.bookId";
			PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
			dispstmt.setInt(1, branchNum);
			ResultSet rsDisp = dispstmt.executeQuery();
			//When the result set is empty start again
			if(!rsDisp.isBeforeFirst()) {
				System.out.println("There are no available books in this branch, sorry.");
				bookCheckout(branchNum);
			}
			System.out.println("ID	Book Name");
			while(rsDisp.next()) {
				int id = rsDisp.getInt("bookId");
				String title = rsDisp.getString("title");
				String authName = rsDisp.getString("authorName");
				System.out.println(id + ")	" + title + " by " + authName);
			}
			System.out.println("0)	Quit to cancel operation");
			idCheck = scan.nextInt();
			if(idCheck == 0) {
				borrower();
			}
			else {
				/*updateQuery = "insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate )"
						+ "values (?, ?, ?, cast(curdate() as datetime), cast((curdate()+7) as datetime)";*/
				updateQuery = "insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate )"
						+ "values (?, ?, ?, current_timestamp, date_add(current_timestamp, interval 7 day))";
				updateQuery2 = "update tbl_book_copies set noOfCopies = noOfCopies - 1 where bookId=? and branchId=?";
				PreparedStatement upstmt = conn.prepareStatement(updateQuery);
				PreparedStatement upstmt2 = conn.prepareStatement(updateQuery2);
				upstmt.setInt(1, idCheck);
				upstmt.setInt(2, branchNum);
				upstmt.setInt(3, cardNum);
				upstmt.executeUpdate();
				upstmt2.setInt(1, idCheck);
				upstmt2.setInt(2, branchNum);
				upstmt2.executeUpdate();
				System.out.println("You have successfully borrowed the book.");
			}
			rsDisp.close();
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void bookReturn(int cardNumber) {
		valid = false;
		System.out.println("Pick the branch you want to return the book to: ");
		System.out.println();
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
			System.out.println("0)	Quit to Previous Menu");
			//Branch ID input
			idCheck = scan.nextInt();
			if(idCheck == 0) {
				borrower();
			}
			else {
				returnBook(idCheck);
			}
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static void returnBook(int branchNum) {
		try {
			//Display table with books borrowed by the user
			displayQuery = "select * from tbl_book_loans join tbl_library_branch on " 
					+ "tbl_library_branch.branchId=tbl_book_loans.branchId join tbl_book_authors on "
					+ "tbl_book_authors.bookId=tbl_book_loans.bookId join tbl_book on "
					+ "tbl_book.bookId=tbl_book_authors.bookId join tbl_author on "
					+ "tbl_author.authorId=tbl_book_authors.authorId "
					+ "where tbl_book_loans.branchId=? and tbl_book_loans.dateIn is null and tbl_book_loans.cardNo=? "
					+ "order by tbl_book.bookId";
			PreparedStatement dispstmt = conn.prepareStatement(displayQuery);
			dispstmt.setInt(1, branchNum);
			dispstmt.setInt(2, cardNum);
			ResultSet rsDisp = dispstmt.executeQuery();
			if(!rsDisp.isBeforeFirst()) {
				System.out.println("You did not borrrow books from this branch.");
				bookCheckout(branchNum);
			}
			else { 
				System.out.println("Select the book you want to return");
				System.out.println("ID	Book Name");
				while(rsDisp.next()) {
					int id = rsDisp.getInt("bookId");
					String title = rsDisp.getString("title");
					String authName = rsDisp.getString("authorName");
					System.out.println(id + ")	" + title + " by " + authName);
				}
				System.out.println("0)	Quit to cancel operation");
				idCheck = scan.nextInt();
				if(idCheck == 0) {
					bookReturn(cardNum);
				}
				else {
					updateQuery = "update tbl_book_loans set dateIn=(current_timestamp) where bookId=? and branchId=? and cardNo=?";
					updateQuery2 = "update tbl_book_copies set noOfCopies = noOfCopies + 1 where bookId=? and branchId=?";
					PreparedStatement upstmt = conn.prepareStatement(updateQuery);
					PreparedStatement upstmt2 = conn.prepareStatement(updateQuery2);
					upstmt.setInt(1, idCheck);
					upstmt.setInt(2, branchNum);
					upstmt.setInt(3, cardNum);
					upstmt.executeUpdate();
					upstmt2.setInt(1, idCheck);
					upstmt2.setInt(2, branchNum);
					upstmt2.executeUpdate();
					System.out.println("Update successful. Thank you for returning the book.");
				}
			}
			rsDisp.close();
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
