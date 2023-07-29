package defaultPackage;

import java.util.Scanner;

public class TryWithResourcesDemo implements AutoCloseable{
	
	public static void main(String[] args) {
		funcTryWithResource();
	}
	
	public static void func() {
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(System.in);
			int number = scanner.nextInt();
			System.out.println(number);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			scanner.close();
		}
	}
	
	public static void funcTryWithResource() {
		try(Scanner scanner = new Scanner(System.in); 
				TryWithResourcesDemo obj = new TryWithResourcesDemo()){
			int number = scanner.nextInt();
			System.out.println(number);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try(TryWithResourcesDemo obj = new TryWithResourcesDemo()){
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		System.out.println("I am running close() Method");
	}

}
