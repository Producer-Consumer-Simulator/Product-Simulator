package producerConsumerApp.GUI;

import producerConsumerApp.models.Product;
import producerConsumerApp.models.Unit;

public class add_product implements Runnable {
	private String text;

	public add_product(String te) {
		this.text = te;
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
		if (c.getfactorqueue().get(0).getproductsqueue().size() == 10) {
			wait();
		}
		c.addProduct(s);
		notifyAll();
	}
}
