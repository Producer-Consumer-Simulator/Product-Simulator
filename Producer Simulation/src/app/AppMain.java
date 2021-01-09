package app;

import app.GUI.mainGUI;

public class AppMain {

	public static void main(String[] args) {

		new Thread(new mainGUI()).start();

	}
}
