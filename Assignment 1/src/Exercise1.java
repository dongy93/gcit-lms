import java.util.*;

public class Exercise1 {
	static final int min = 0;
	static final int max = 100;
	
	//Generate a random integer between -1000 and 1000 
	public static int randomInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Scanner for input
		Scanner scan = new Scanner(System.in);
		
		//Set the value to compare with to the random integer
		int corrVal = randomInt(min, max);
		System.out.println("Guess a number between 0 and 100");
		//5 chances for user to guess

		for(int i=0; i<5; i++) {	
				//Only take integers as valid numbers
				if(!scan.hasNextInt()) {
					System.out.println("Please only input integers");
					scan.next();
					continue;
				}
				int inputInt = scan.nextInt();
				if(inputInt <= corrVal + 10 && inputInt >= corrVal - 10) {
					System.out.println("Congratulations! The number was " + corrVal);
					System.exit(0);
				}
				else {
					System.out.println("Sorry that was not close enough, please try again");
				}
		}
		System.out.println("Sorry, that was too many attempts. The correct value was " + corrVal);
		System.exit(0);
			// lowerBound <= userGuess && userGuess <= upperBound
	}
}
