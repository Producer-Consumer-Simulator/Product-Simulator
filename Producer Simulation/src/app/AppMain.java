package app;

import app.GUI.mainGUI;


public class AppMain {

	public static void main(String[] args) {

		new Thread(new mainGUI()).start();
		//new Thread(new productGui()).start();
		/*ProducerConsumer pc = ProducerConsumer.getInstance();
		Thread t1 = new Thread(new Runnable() { 
            @Override
            public void run() 
            { 
            	pc.Consume(); 
            } 
        });
		t1.start();*/
	}
}
