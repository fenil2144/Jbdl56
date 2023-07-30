package defaultPackage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
	
	public static void main(String[] args) {
		
		int[] integers = {1,2,3,4,5,6};
		IntStream intStream = Arrays.stream(integers);
		
		System.out.println(intStream.min().getAsInt());
		
		//Primitive Stream to List
		List<Integer> listOfIntegers = IntStream.of(1,2,3,4,5).boxed()
				.collect(Collectors.toList());
	}

}
