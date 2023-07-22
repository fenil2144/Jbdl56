package defaultPackage;

public class Dog implements Cloneable{
	
	public String name;
	private static String breed;
	int age;
	private String color;
	
	public void eat() {
		System.out.println("I am eating");
	}
	
	public void bark() {
		System.out.println("I can bark!");
	}
	
	public void display() {
		System.out.println("Name: "+name+" ,Breed: "+breed);
		
		int name;
	}
	
	public Dog returnObj() {
		return this;
	}
	
	public Dog() {
		
	}
	
	public Dog(String diff) {
		this.name = diff;
		System.out.println("Dog constructor with 1 argument");
	}
	
	public Dog(int drefkrn) {
//		this.name = diff;
		System.out.println("Dog constructor with 1 argument");
	}
	
	public Dog(String name, int age) {
		this(age);
		this.age = age;
		System.out.println("Dog constructor with 2 argument");

	}
	
	public static void main(String[] args) {
		
		//Ways of Creating Object
		Dog dog = new Dog();
		dog.name="Tom";
		dog.breed="German";
		dog.age= 10;
		
//		dog.display();
		
		Dog dog2 = new Dog();
		dog2.name="Tummy";
		dog.breed="Voda";

		dog2.age= 15;
		
		Dog dogWithName = new Dog("Jimmy",10);
		
//		dog2.display();
//		dog.display();
//		System.out.println(Dog.breed);
		
		
		//2 - Clone Method
		try {
			Dog dogCloneObject = (Dog) dog.clone();
			dogCloneObject.display();
			
			Dog dogCloneObject2 = new Dog();
			dogCloneObject2.name = dog.name;
			
			System.out.println(dog.equals(dogCloneObject));
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		//3 - Deserialization
//		inputstream
//		Object obj = is.read();
//		
//		Dog dog = new Dog();
//		dog.name = JSONOBJEctmapper.read("name");
		
		System.out.println(dog);
		System.out.println(dog.returnObj());

	}

}
