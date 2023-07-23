package defaultPackage;

class Parent{
	int a  = 20;
	
	void method1() {
		System.out.println("From Parent Class");
	}
	protected void protectedMethod() {
		System.out.println("I am Protected Method");
	}
}

class Child extends Parent{
	int a = 10;
	public void method1() {
		super.method1();
		System.out.println("From Child Class");
	}
}

public class MethodOverriding {
	public static void main(String[] args) {
		Parent parentObj = new Parent();
		parentObj.method1();
		System.out.println(parentObj.a);
		
		Child childObj = new Child();
		childObj.method1();
		System.out.println(childObj.a);
		
		Parent parentRef = new Child();
		parentRef.method1();		//child class
		System.out.println(parentRef.a);
		
		

	}
	


}
