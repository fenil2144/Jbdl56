package defaultPackage;

class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String s) {
		super(s);
	}
	
}

public class CustomExceptionDemo {
	
	public static void main(String[] args) {
		try {
			//encountered the situation where user is not found
			throw new UserNotFoundException("User with id: 123 doesn't exist!");
		}catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
