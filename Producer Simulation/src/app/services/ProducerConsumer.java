package app.services;

import app.models.Product;
import app.models.Unit;
import app.services.SnapShot.Originator;

public class ProducerConsumer {
	Unit u = Originator.getInstance().getState();
	private static ProducerConsumer instance;
	private ProducerConsumer() {
		
	}
	public static ProducerConsumer getInstance() {
		if (instance == null)
			instance = new ProducerConsumer();
		return instance;
	}
	
	public void produce(Product p) {
		synchronized (this) {
			while (u.getFirstQueue().isFullProductQueue()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			u.addProduct(p);
			notify();
		}
	}
	
	
	public void Consume() {
		
		while (true) {
			synchronized (this) {
				while (u.getFirstQueue().getproductsqueue().isEmpty()) {
					try {
						System.out.println("Consume is Wait");
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				u.getFirstQueue().Simulate(u.getFirstQueue().getProductsQueue().poll());
				notify();
			}
		}
	}
	
	

}
