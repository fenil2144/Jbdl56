package defaultPackage;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequesExample {
	
	public static void main(String[] args) {
		Deque<Integer> arrayDequeObj = new ArrayDeque<Integer>();
		
		arrayDequeObj.addFirst(1);
		arrayDequeObj.addFirst(2);
		arrayDequeObj.addLast(10);
		arrayDequeObj.addLast(20);
		System.out.println(arrayDequeObj);

	}

}
