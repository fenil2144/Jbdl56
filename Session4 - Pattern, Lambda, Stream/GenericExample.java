package defaultPackage;

class Test<T>{
	T obj;
	Test(T obj){
		this.obj = obj;
	}
	public T getObject() {
		return this.obj;
	}
}

public class GenericExample {
	
	public static void main(String[] args) {
		//Integer Type
		Test<Integer> integerObj = new Test<Integer>(14);
		System.out.println(integerObj.getObject());
		
		Object o = new Object();
		o = 10;
		o= "Java";
		
		Test<String> sObj = new Test<String>("Java");
		System.out.println(sObj.getObject());
		
//		System.out.println(integerObj = sObj);

		
	}

}
