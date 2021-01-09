package producerConsumerApp;

import producerConsumerApp.GUI.mainGUI;

public class AppMain {

	public static void main(String[] args) {

		new Thread(new mainGUI()).start();

	}
}
