import java.util.*; 
public class Exercise1 {     
	public static void main( String [] args ) {         
		// Create a set called mySet         
		Set mySet = new HashSet();         
		// Ensure that this set contains an interesting selection of fruit           
		String fruit1 = "pear", fruit2 = "banana", fruit3 = "tangerine",                                                         
				fruit4 = "strawberry", fruit5 = "blackberry";         
		mySet.add( fruit1 );         
		mySet.add( fruit2 );         
		mySet.add( fruit3 );         
		mySet.add( fruit2 );         
		mySet.add( fruit4 );         
		mySet.add( fruit5 );         
		// Display contents of mySet         
		System.out.println( "mySet now contains:" );         
		System.out.println( mySet );     
		// Display the number of elements of mySet
		System.out.println("There are now " + mySet.size() + " elements in mySet");
		// Remove fruit as necessary
		mySet.remove("blackberry");
		mySet.remove("strawberry");
		System.out.println("mySet now contains:");
		System.out.println(mySet);
		// Clear the list using a single method invocation
		mySet.clear();
		System.out.println("Is the set now empty? " + mySet.isEmpty());
	}
}