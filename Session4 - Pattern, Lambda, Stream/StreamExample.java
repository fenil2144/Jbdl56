package defaultPackage;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {
	
	public static void main(String[] args) {
		
		List<Integer> number = Arrays.asList(1,2,3,4,5);
		
		//Without Lambda - using Annonymous Class
		System.out.println(number.stream().filter(new Predicate<Integer>() {

			@Override
			public boolean test(Integer t) {
				System.out.println("In Test Method With t= "+t);
				return t%2==0;
			}
			
		}).map(new Function<Integer, Integer>() {

			@Override
			public Integer apply(Integer t) {
				System.out.println("In apply Method With t= "+t);
				return t*t;
			}
		}).collect(Collectors.toList())
		);
		
		System.out.println(number.stream().filter(t -> t%2==0).map(x -> x*x)
				.collect(Collectors.toList()));
		System.out.println("In Main Method");
		
		List<String> names = Arrays.asList("Java","Python","Geeks","Node");
		Set<String> result = names.stream().filter(s -> s.startsWith("G"))
				.map(s -> s.toUpperCase()).collect(Collectors.toSet());
		System.out.println(result);
		
	}

}
