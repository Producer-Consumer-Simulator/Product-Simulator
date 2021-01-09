package app.models;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UnitQueue implements Runnable {

	private String Name;
	private BlockingQueue<Product> productsQueue;
	private ArrayList<Machine> availableMachines;
	public boolean lastQueue = false;
	// private ArrayList<Thread> availableMachinesThreads;
	// private ArrayList<Machine> notAvailableMachines;

	public UnitQueue(String Name) {
		this.Name = Name;
		this.productsQueue = new ArrayBlockingQueue<Product>(10);
		this.availableMachines = new ArrayList<Machine>();
		// this.setNotAvailableMachines(new ArrayList<Machine>());
		// this.availableMachinesThreads = new ArrayList<Thread>();
	}

	public BlockingQueue<Product> getproductsqueue() {
		return productsQueue;
	}

	public void addAvailableMachine(Machine m) {
		for (int i = 0; i < this.availableMachines.size(); i++) {
			if (this.availableMachines.get(i).getName().equalsIgnoreCase(m.getName()))
				return;
		}
		this.availableMachines.add(m);
	}

	public void addProduct(Product p) {
		this.productsQueue.add(p);
	}

	/*
	 * public void addAvailableMachineThread(Thread t) {
	 * //this.availableMachinesThreads.add(t); }
	 */

	public BlockingQueue<Product> getProductsQueue() {
		return productsQueue;
	}

	public void setProductsQueue(BlockingQueue<Product> productsQueue) {
		this.productsQueue = productsQueue;
	}

	public ArrayList<Machine> getAvailableMachines() {
		return availableMachines;
	}

	public void setAvailableMachines(ArrayList<Machine> availableMachines) {
		this.availableMachines = availableMachines;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isFullProductQueue() {
		return this.productsQueue.size() == 10;
	}

	public void SimulateOld() {
		// check available
		// check machine
		// for
		while (!this.productsQueue.isEmpty() && !this.lastQueue) {
			/*
			 * while(this.productsQueue.poll() == null ) { try { wait(); } catch
			 * (InterruptedException e) { e.printStackTrace(); } }
			 */
			Product product = this.productsQueue.peek();
			if (product != null) {

				while (this.availableMachines.isEmpty() && !this.lastQueue) {
					// wait();
				}
				// for(int i = 0 ; i < this.availableMachines.size() ; i++) {
				// if(this.availableMachines.get(i).isAvalible()) {
				// synchronized(this.availableMachines.get(i)) {
				if (!this.availableMachines.isEmpty()) {
					this.productsQueue.poll();
					// System.out.println("Ahmed" + product.getColor() );
					this.availableMachines.get(0).setProduct(product);
					Thread t = new Thread(this.availableMachines.get(0));
					t.start();

					// this.availableMachinesThreads.add(new Thread(this.availableMachines.get(i)));
					// Thread t = this.availableMachinesThreads.get(i);
					// synchronized (this.availableMachines.get(i).getProduct()) {
					// Thread t = new Thread(this.availableMachines.get(0));
					// t.start();
					// if(!this.availableMachines.isEmpty())
					this.availableMachines.remove(0);
				}
				// this.notAvailableMachines.add(this.availableMachines.remove(0));
				// }
				// break;
				// }
				// }
				// }
			}

			/*
			 * while (this.productsQueue.isEmpty()) {
			 * 
			 * }
			 */

		}

	}

	public void Simulate(Product p) {
		Machine available = getAvailableMachine();
		while (available == null) {
			available = getAvailableMachine();
		}
		available.setProduct(p);
		available.setAvailable(false);
		// synchronized (available.getProduct()) {
		new Thread(available).start();
	}

	private Machine getAvailableMachine() {
		for (int i = 0; i < this.availableMachines.size(); i++) {
			if (this.availableMachines.get(i).isAvalible()) {
				return this.availableMachines.get(i);
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "UnitQueue [Name=" + Name + ", productsQueue=" + productsQueue + ", availableMachines="
				+ availableMachines + "]";
	}

	@Override
	public void run() {
		while (!this.productsQueue.isEmpty()/* && !this.lastQueue */) {
			if (this.lastQueue) {
				Unit u = Unit.getInstance();
				for (int i = 0; i < this.productsQueue.size(); i++) {
					u.getFinishedProducts().add(this.productsQueue.poll());
				}
			}
			synchronized (productsQueue) {
				Product product = this.productsQueue.poll();
				if (product != null) {
					Machine available = getAvailableMachine();
					while (available == null) {
						available = getAvailableMachine();
					}
					available.setProduct(product);
					available.setAvailable(false);
					// synchronized (available.getProduct()) {
					new Thread(available).start();
				}
			}
		}
	}

	/*
	 * public ArrayList<Machine> getNotAvailableMachines() { return
	 * notAvailableMachines; }
	 * 
	 * public void setNotAvailableMachines(ArrayList<Machine> notAvailableMachines)
	 * { this.notAvailableMachines = notAvailableMachines; }
	 */

	/*
	 * public ArrayList<Thread> getAvailableMachinesThreads() { return
	 * availableMachinesThreads; }
	 * 
	 * public void setAvailableMachinesThreads(ArrayList<Thread>
	 * availableMachinesThreads) { this.availableMachinesThreads =
	 * availableMachinesThreads; }
	 */

}