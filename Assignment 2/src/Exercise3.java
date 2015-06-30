import java.io.*;
import java.util.*;
import java.text.*;
public class Exercise3 {

	public static void main(String[] args) throws IOException {
		HashMap<String, Double> hash = new HashMap<>();
		//List<Double> listMarks = new ArrayList<Double>();
		/*List<String> listName = new ArrayList<String>();
		List<Double> listMarks = new ArrayList<Double>();
		*/
		// TODO Auto-generated method stub
			Scanner reader = new Scanner(new File("students.txt"));
			while(reader.hasNext()) {
				String name = reader.next();
				//listName.add(name);
				double mark = reader.nextDouble();
				hash.put(name, mark);
			}
			Set set1 = hash.entrySet();
			Iterator iter1 = set1.iterator();
			Map<String, Double> treeMap = new TreeMap<String, Double>(hash);
			Set set2 = treeMap.entrySet();
			Iterator iter2 = set2.iterator();
			System.out.println("Alpha Order");
			while(iter2.hasNext()) {
				Map.Entry name2 = (Map.Entry)iter2.next();
				System.out.print(name2.getKey() + " ");
				System.out.println(name2.getValue());
			}
			Map<String, Double> Map2 = sortByValues(hash);
			System.out.println();
			System.out.println("Merit Order");
			Set set3 = Map2.entrySet();
			Iterator iter3 = set3.iterator();
			while(iter3.hasNext()) {
				Map.Entry name3 = (Map.Entry)iter3.next();
				System.out.print(name3.getKey() + " ");
				System.out.println(name3.getValue());
			}
	} 
	private static HashMap sortByValues(HashMap hash) { 
		List list1 = new LinkedList(hash.entrySet());
		Collections.sort(list1, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
		      	}
		});
	
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator iter = list1.iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
			} 
		return sortedHashMap;
	}
}




