package defaultPackage;

abstract class CentralBank{
	
	abstract int getRateOfInterest();
	abstract void withdraw(int amount);
	abstract void deposit(int amount);
	
	public void openAccount() {
		System.out.println("I am opening Account");
	}
}

class SBI extends CentralBank{

	@Override
	int getRateOfInterest() {
		return 6;
	}

	@Override
	void withdraw(int amount) {
		System.out.println("Performing Withdrawl at SBI Bank");
	}

	@Override
	void deposit(int amount) {
		System.out.println("Performing Deposit at SBI Bank");
	}
	
}

class SingaporeBank extends CentralBank{

	@Override
	int getRateOfInterest() {
		return 7;
	}

	@Override
	void withdraw(int amount) {
		System.out.println("Performing Withdrawl at Singapore Bank");
	}

	@Override
	void deposit(int amount) {
		System.out.println("Performing Deposit at Singapore Bank");
	}
	
	@Override
	public void openAccount() {
		System.out.println("I am opening Account with Singapore Bank");
	}
	
	public void openFixedDepositAccount() {
		System.out.println("I am opening Account with Fixed Deposit Account");
	}
	
}

abstract class anotherAbstractClass extends CentralBank{
	
}


public class AbstractExample {
	
	public static void main(String[] args) {
		
		SBI sbiObj = new SBI();
		sbiObj.deposit(10);
		
		CentralBank cb = new SingaporeBank();
		
	}

}
