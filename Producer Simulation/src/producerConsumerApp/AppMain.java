package producerConsumerApp;

import producerConsumerApp.GUI.mainGUI;


public class AppMain {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new mainGUI());
		t1.start();
		
	}
}
