package app.models;

/*import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import producerConsumerApp.GUI.model.DecoShape;
import producerConsumerApp.services.UnitBuilder;
import producerConsumerApp.services.SnapShot.Memento;
import producerConsumerApp.services.SnapShot.Originator;*/

public class Consumer implements Runnable {
	Unit u = Unit.getInstance();

	@Override
	public void run() {
		
		while (true) {
			synchronized (this) {
				while (u.getFirstQueue().getproductsqueue().isEmpty()) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//u.Simulate();
				u.getFirstQueue().Simulate(u.getFirstQueue().getProductsQueue().poll());
				notify();
			}
		}
	}
	

}
