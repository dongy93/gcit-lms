import java.util.*;

public class Exercise2 {

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		list1.add("pear");
		list1.add("banana");
		list1.add("tangerine");
		list1.add("strawberry");
		list1.add("blackberry");
		//Display in order of insertion using iterator
		Iterator<String> fruitIterator = list1.iterator();
		while(fruitIterator.hasNext()) {
			System.out.println(fruitIterator.next());
		}
		System.out.println();
		//Display in reverse order using ListIterator
		ListIterator<String> listIter = list1.listIterator(list1.size());
		while(listIter.hasPrevious()) {
			System.out.println(listIter.previous());
		}
		// TODO Auto-generated method stub
		System.out.println();
		list1.add(3, "banana");
		Iterator<String> fruitIterator2 = list1.iterator();
		//Display in order of insertion using iterator
		while(fruitIterator2.hasNext()) {
			System.out.println(fruitIterator2.next());
		}
		System.out.println();
		//Display in reverse order using ListIterator
		ListIterator<String> listIter2 = list1.listIterator(list1.size());
		while(listIter2.hasPrevious()) {
			System.out.println(listIter2.previous());
		}
	}

}
