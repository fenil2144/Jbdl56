package defaultPackage;

public class MethodOverloading {
	
	public int sum(int a,int b) {
		return (a+b);
	}
	
//	public double sum(int a,int b) {
//		return (a+b);
//	}
	
//	public double sum(double a,double b) {
//		return (a+b);
//	}
	
	public double sum(double a,double b, double c) {
		System.out.println("a= "+a);
		return (a+b+c);
	}
	
	public static void main(String[] args) {
		MethodOverloading methodOverloadingObj = new MethodOverloading();
		System.out.println(methodOverloadingObj.sum(10,20));
		System.out.println(methodOverloadingObj.sum(10.1,20.2,30.3));
//		System.out.println(methodOverloadingObj.sum(10.1,20.2));
		System.out.println(methodOverloadingObj.sum(10,20,30));

		
	}

}
