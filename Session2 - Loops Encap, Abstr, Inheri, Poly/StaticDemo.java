package defaultPackage;

class Student{
	String name;
	int rollNo;
	final int a = 10;

	static String schoolName;
	
	static int counter = 0;
	
	public Student(String name) {
		this.name = name;
		this.rollNo = setRollNo();
	}
	
	static int setRollNo() {
		counter++;
		return counter;
	}
	
	static void setSchoolName(String name) {
		schoolName = name;
	}
	
	static class NestedClass{
		
	}
}


public class StaticDemo {
	static {
		System.out.println("This is Static Block");
	}
	
	public static void main(String[] args) {
		System.out.println("This is Main Method");
	}
	
	

}
