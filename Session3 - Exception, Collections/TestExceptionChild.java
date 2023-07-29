package defaultPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class ParentClass{
	
	void msg() throws FileNotFoundException,IOException{
		FileReader fileReader = new FileReader("");
		
		System.out.println("Parent Class Method");
		throw new ArithmeticException();
	}
}

public class TestExceptionChild extends ParentClass{
	
	void msg() throws IOException{
		System.out.println("Parent Class Method");
	}
}
