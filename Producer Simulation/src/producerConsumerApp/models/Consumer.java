package producerConsumerApp.models;

/*import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import producerConsumerApp.GUI.model.DecoShape;
import producerConsumerApp.services.UnitBuilder;
import producerConsumerApp.services.SnapShot.Memento;
import producerConsumerApp.services.SnapShot.Originator;*/

public class Consumer implements Runnable {

	@Override
	public void run() {
		Unit u = Unit.getInstance();
		u.Simulate();
	}

	/*
	 * public static void main(String[] args) { UnitBuilder ub = new UnitBuilder();
	 * ub.CreateMachine(new DecoShape(), "q0", "q1"); ub.CreateMachine(new
	 * DecoShape(), "q1", "q2"); ub.CreateMachine(new DecoShape(), "q1", "q3");
	 * //ub.CreateMachine("ddd", 10, "q3", "q2"); Unit u = ub.toUnit(); Originator o
	 * = new Originator(); o.setState(u); BlockingQueue<Product> productsQueue = new
	 * ArrayBlockingQueue<Product>(10); productsQueue.add(new Product("uu",
	 * "yellow")); productsQueue.add(new Product("pp", "gray"));
	 * o.getState().addProduct(new Product("uu", "yellow"));
	 * o.getState().addProduct(new Product("pp", "gray"));
	 * //o.getState().getQueue("q0").setProductsQueue(productsQueue); Memento m =
	 * o.saveStateToMemonto(o.getState());
	 * 
	 * o.getState().Simulate(); /*productsQueue.add(new Product("oo", "red"));
	 * productsQueue.add(new Product("vv", "green")); productsQueue.add(new
	 * Product("bb", "blue"));
	 */
	// u.getQueue("q0").setProductsQueue(productsQueue);
	// u.Simulate();
	// System.out.println("000");
	// m.getState().Simulate();

	// }*/

}
