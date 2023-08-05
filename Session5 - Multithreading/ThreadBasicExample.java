package defaultPackage;

class ThreadA extends Thread {
	
	public void run() {
		System.out.println("ThreadA "+Thread.currentThread());
		for(int i=20;i>0;i--) {
			System.out.println("ThreadA class with i= "+i);
		}
	}
	
}
class ThreadB extends Thread {
	
	public void run() {
		System.out.println("ThreadB "+Thread.currentThread());
		for(int i=20;i>0;i--) {
			System.out.println("ThreadB class with i= "+i);
		}
	}
	
}

class ThreadRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println("ThreadRunnable "+Thread.currentThread());
		for(int i=20;i>0;i--) {
			System.out.println("ThreadRunnable class with i= "+i);
		}
	}
	
}

public class ThreadBasicExample {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread());
		
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		threadA.setDaemon(true);
		threadA.isDaemon();

		threadA.start();
		ThreadA threadA2 = new ThreadA();
		threadA2.start();
		threadA.join();

		threadB.start();
//		threadA.start(); -- throws exception
//		threadA.run(); -- possible

		ThreadRunnable threadC = new ThreadRunnable();
		Thread t1 = new Thread(threadC);
		t1.setPriority(10);
		t1.start();
//		return Employee.get(addrss)
		
	}

}
