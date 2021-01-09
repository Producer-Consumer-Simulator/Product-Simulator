package app;

import app.GUI.mainGUI;
import app.GUI.productGui;

public class AppMain {

	public static void main(String[] args) {

		new Thread(new mainGUI()).start();
		new Thread(new productGui()).start();
	}
}
