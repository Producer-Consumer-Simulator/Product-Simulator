package app.models;

public class ProducerConsumer {
	Unit u = Unit.getInstance();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			u.addProduct(p);
			System.out.println("/n/ni'm here produce");
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
