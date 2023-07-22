package defaultPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationDemo{

	public static void main(String[] args) {
		
		DemoClass demoObj = new DemoClass(10, "Java");
		String filename = "temp.txt";
		
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(demoObj);
			out.close();
			file.close();
			System.out.println("Serializaton successful!");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Deserialization
		try {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			
			DemoClass derserializedObj = (DemoClass) in.readObject();
			System.out.println(derserializedObj);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
class DemoClass implements Serializable{
	private static final long serialVersionUID = 1L;
	public int a;
	public String b;
	
	DemoClass(int a, String b){
		this.a = a;
		this.b  =b;
	}

	@Override
	public String toString() {
		return "DemoClass [a=" + a + ", b=" + b + "]";
	}
	
	
}
