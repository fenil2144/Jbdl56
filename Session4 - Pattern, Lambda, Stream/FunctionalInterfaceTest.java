package defaultPackage;

@FunctionalInterface
interface funInterface{
	int getSum(int a , int b);
}

//class funInterfaceImpl implements funInterface{
//
//	@Override
//	public int getSum(int a, int b) {
//		return (a+b);
//	}
//	
//}

public class FunctionalInterfaceTest {
	
	public static void main(String[] args) {
		
		funInterface funInterfaceRef = new funInterface() {

			@Override
			public int getSum(int a, int b) {
				return (a+b);
			}
			
		};
		
		// (Argument List) -> {Body}
		funInterfaceRef = (a,b) -> {return (a+b);};
		
		System.out.println(funInterfaceRef.getSum(10, 14));
		
	}

}
