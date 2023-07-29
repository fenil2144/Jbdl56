package defaultPackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class ArrayListExample {
	
	public static void main(String[] args) {
		List<Integer> list = new LinkedHashSet()<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		System.out.println(list);
		
		list.remove(new Integer(2));
		System.out.println(list);
		
		list.set(0, 2);	
		System.out.println(list);
		
		ArrayList<ArrayList<Integer>> twoDList = new ArrayList<ArrayList<Integer>>();
		twoDList.add(new ArrayList<>());
		twoDList.get(0).add(1);
		System.out.println(twoDList);
		
		Iterator<Integer> iteratorObj = list.iterator();
		while(iteratorObj.hasNext()) {
			System.out.println(iteratorObj.next());
			
		}
//		iteratorObj.forEachRemaining();
		
		for(Integer x: list) {
			System.out.println(x);
		}

	}
	

}
