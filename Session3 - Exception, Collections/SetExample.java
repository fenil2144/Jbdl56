package defaultPackage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetExample {
	
	public static void main(String[] args) {
		
		Set<String> stringSet = new HashSet<String>();
		stringSet.add("ABC");
		stringSet.add("BCD");
		stringSet.add("Java");
		stringSet.add("Python");
		stringSet.add("C++");
		stringSet.add("NodeJs");

		
//		for(int i=0;i<100;i++) {
//			intSet.add(i);
//		}
		Iterator<String> iteratorObj = stringSet.iterator();
		while(iteratorObj.hasNext()) {
			System.out.println(iteratorObj.next());
		}
//		Iterator<Integer> iteratorObj2 = intSet.iterator();
//
//		while(iteratorObj2.hasNext()) {
//			System.out.println(iteratorObj2.next());
//		}
		System.out.println(stringSet);
		System.out.println(stringSet);
	}

}
