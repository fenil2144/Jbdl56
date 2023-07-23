package defaultPackage;

interface Language{
	
	void getName(String name);
	void getNumberOfCharachters();
	int i = 10;
	default void printSyntax() {
		System.out.println("I am Printing Syntax");
	}
}

interface programmingLanguage{
	
}

class JavaLanguage extends CentralBank implements Language,programmingLanguage {

	@Override
	public void getName(String name) {
		System.out.println("name= "+name);
	}

	@Override
	public void getNumberOfCharachters() {
		System.out.println("I am returning the number of charachters");
	}

	@Override
	int getRateOfInterest() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	void withdraw(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void deposit(int amount) {
		// TODO Auto-generated method stub
		
	}
	
}

public class InterfaceExample {
	
	JavaLanguage javaLanguageObj = new JavaLanguage();
	

}
