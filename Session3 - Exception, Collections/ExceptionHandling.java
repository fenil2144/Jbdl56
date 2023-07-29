package defaultPackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

public class ExceptionHandling {
	
	public static void main(String[] args) {
//		try {
//			FileReader fileReader = new FileReader("/Users/fenilshah/temp.txt");
//			code to connect to db
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} 
//		catch(SQLException e) {
//			System.out.println("Action Performed on SQLException");
//		}
		
		try {
			computeDivision(0);

		} catch (ArithmeticException | NullPointerException e) {
			System.out.println("Incorrect Number Passed!");
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("Incorrect Number Passed!");
			e.printStackTrace();
		} finally {
			System.out.println("I am in finally");
		}
		System.out.println("I am some useful code");
		
		

	}
	
	public static void computeDivision(int number) {
		int x = 100/number;
		System.out.println("Result: "+x);
	}
	

}
