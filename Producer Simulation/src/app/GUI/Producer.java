package app.GUI;

import app.models.Product;
import app.models.Unit;
import app.services.SnapShot.Originator;

public class Producer implements Runnable {
	private String text;
	
	public Producer(String text ) {
		this.text=text;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			add(new Product(text));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public synchronized void add(Product s) throws InterruptedException {
		Unit c = Originator.getInstance().getState();
		while (c.getFirstQueue().isFullProductQueue()) {
			wait();
		}
		c.addProduct(s);
		notify();
	}
}
