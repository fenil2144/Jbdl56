package defaultPackage;

import java.util.Arrays;
import java.util.List;

public class ParallelStreamExample {
	
	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("J","A","V","A","B","A","C","K");
		list.parallelStream().forEach(System.out::print);
		//CJVAAKAB
		//ABJKCAVA
		
		//To get Number of Cores
		System.out.println("Avlbl Cores: "+Runtime.getRuntime().availableProcessors());
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		numbers.parallelStream().filter(i -> {
			System.out.println("In Filter Method: "+i+", thread = "+Thread.currentThread().getName());
			return i%2==0;
		}).map(i -> {
			System.out.println("In Map Function: "+i+" , thread = "+Thread.currentThread().getName());
			return i*i;
		}).forEachOrdered(x -> {
			System.out.println("In For Each Function: "+x+" , thread = "+Thread.currentThread().getName());
		});
	}

}
