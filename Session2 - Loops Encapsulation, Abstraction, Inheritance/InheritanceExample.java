package defaultPackage;

class Animal{
	String name;
	int age;
	
	public void eat() {
		System.out.println("I am eating!");
	}
	
	public void run() {
		System.out.println("I am running!");
	}
}

class DogAnimal extends Animal{
	
	public void bark() {
		System.out.println("I am Barking!");
	}
}

class CatAnimal extends Animal{
	public void Meow() {
		System.out.println("I am Meowing!");
	}
}

public class InheritanceExample {
	
	public static void main(String[] args) {
		DogAnimal dogAnimal = new DogAnimal();
		dogAnimal.eat();
		dogAnimal.bark();
		
		CatAnimal catAnimal = new CatAnimal();
		catAnimal.run();
	}
	
	

}
