package producerConsumerApp.GUI;

import producerConsumerApp.models.Product;
import producerConsumerApp.models.Unit;

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
		Unit c = Unit.getInstance();
		while (c.getFirstQueue().isFullProductQueue()) {
			wait();
		}
		c.addProduct(s);
		notify();
	}
}
