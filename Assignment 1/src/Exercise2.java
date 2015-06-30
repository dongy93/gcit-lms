import java.util.*;

public class Exercise2 {
	private static String player1;
	private static String player2;
	private static int player1Chips;
	private static int player2Chips;
	private static int totalChips;
	private static int turnCounter;
	//Used for checking that game rules are followed
	private static boolean checker;
	//Used for checking number of chips are valid to start
	private static boolean chipChecker;
	
	public static void Game() {
		Scanner scan = new Scanner(System.in);
		int chipRemoval;
		checker = true;
		//Increasing turn number every time one's taken
		turnCounter++;
		System.out.println(player1 + " has " + player1Chips + ".");
		System.out.println(player2 + " has " + player2Chips + ".");
		//Odd number turns starting at 1
		if(turnCounter%2 == 1) {	
			System.out.println("It is your turn " + player1);
			//Condition is different when only one chip is left in the game
			if(totalChips == 1) {
				System.out.println("There is 1 chip remaining.");
				System.out.println("You may take any number of chips from 1 to 1. How many will you take, " + player1 + "?");
				chipRemoval = scan.nextInt(); 
				while(checker == true) {
					if(chipRemoval != 1) {
						System.out.println("You must take 1");
						System.out.println("How many will you take " + player1 + "?");
						chipRemoval = scan.nextInt();
						System.out.println();
					}
					else {
						checker = false;
					}
				}
			}
			//When there are more than 1 chip left
			else {
				System.out.println("There are " + totalChips + " chips remaining.");
				System.out.print("You may take any number of chips from 1 to " + totalChips/2 + ". How many will you take, " + player1 + "?");
				chipRemoval = scan.nextInt();
				while(checker == true) {
					if(chipRemoval < 1) {
						System.out.println("Illegal move: You must take at least one chip.");
						System.out.print("How many will you take " + player1 + "?");
						chipRemoval = scan.nextInt();
						System.out.println();
					}
					else if(chipRemoval > totalChips/2) {
						System.out.println("Illegal move: You may not take more than " + totalChips/2 + " chips.");
						System.out.print("How many will you take " + player1 + "?");
						chipRemoval = scan.nextInt();
						System.out.println();
					}
					else {
						checker = false;
					}
				}
			}
			System.out.println();
			//Chips from total taken by player are registered
			totalChips -= chipRemoval;
			player1Chips += chipRemoval;
		}
		//Even number turns taken by player 2
		else if(turnCounter%2 == 0) {	
			System.out.println("It is your turn " + player2);
			//Conditions are different when there's only 1 chip left
			if(totalChips == 1) {
				System.out.println("There is 1 chip remaining.");
				System.out.println("You may take any number of chips from 1 to 1. How many will you take, " + player2 + "?");
				chipRemoval = scan.nextInt(); 
				while(checker == true) {
					if(chipRemoval != 1) {
						System.out.println("You must take 1");
						System.out.println("How many will you take " + player2 + "?");
						chipRemoval = scan.nextInt();
						System.out.println();
					}
					else {
						checker = false;
					}
				}
			}
			//When there are more than 1 chip left in the game
			else {
				System.out.println("There are " + totalChips + " chips remaining.");
				System.out.print("You may take any number of chips from 1 to " + totalChips/2 + ". How many will you take, " + player2 + "?");
				chipRemoval = scan.nextInt();
				while(checker == true) {
					if(chipRemoval < 1) {
						System.out.println("Illegal move: You must take at least one chip.");
						System.out.print("How many will you take " + player2 + "?");
						chipRemoval = scan.nextInt();
						System.out.println();
					}
					else if(chipRemoval > totalChips/2) {
						System.out.println("Illegal move: You may not take more than " + totalChips/2 + " chips.");
						System.out.print("How many will you take " + player2 + "?");
						chipRemoval = scan.nextInt();
						System.out.println();
					}
					else {
						checker = false;
					}
				}
			}
			System.out.println();
			//Chips taken by player during the turn are registered
			totalChips -= chipRemoval;
			player2Chips += chipRemoval;
		}

	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*String player1;
		String player2;
		int totalChips;*/
		
		//toPlay boolean checks if game should be played
		boolean toPlay = true;
		boolean playerChecker = true;
		String toPlayString;
		while(toPlay == true) {
			player1Chips = 0;
			player2Chips = 0;
			System.out.print("What is the name of the first player? ");
			player1 = scan.next();
			System.out.println();
			System.out.print("What is the name of the second player? ");
			player2 = scan.next();
			System.out.println();
			while(player1.toUpperCase().equals(player2.toUpperCase())) {
				System.out.print("Both players cannot be named " + player1 + ". Enter a different name: ");
				player2 = scan.next();
				System.out.println();
			}
			System.out.print("How many chips does the initial pile contain? ");
			totalChips = scan.nextInt();
			System.out.println();
			//Making sure that the same conditions are looped and checked for invalid total chip numbers
			while(playerChecker == true) {
				if(totalChips < 3) {
					System.out.print("You have to start with at least 3 chips. Choose another number: ");
					totalChips = scan.nextInt();
					System.out.println();
				}
				else if(totalChips%2 == 0) {
					System.out.print("You have to start with an odd number of chips. Choose another number: ");
					totalChips = scan.nextInt();
					System.out.println();
				}
				else {
					playerChecker = false;
					System.out.println();
					System.out.println("**********************");
					System.out.println();
					while(totalChips != 0) {
						Game();
					}
					System.out.println(player1 + " has " + player1Chips + " chips.");
					System.out.println(player2 + " has " + player2Chips + " chips.");
					//Checking the turn counter to see who took the last chip
					if(turnCounter%2 == 1) {
						System.out.println(player2 + " wins!");
					}
					else if(turnCounter%2 == 0) {
						System.out.println(player1 + " wins!");
					}					
				}
			}
			System.out.println("Play another game? (y/n) ");
			toPlayString = scan.next();
			//Allowing user to use 'y' or 'Y' to express the wish to continue playing
			if(toPlayString.toUpperCase().equals("Y")) {
				toPlay = true;
			}
			//Ends the run whenever user inputs anything other than 'y'
			else {
				toPlay = false;
				System.exit(0);
			}
		}
	}
}
