package models;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UnitQueue {
	

	private String Name;
	private BlockingQueue<Product> productsQueue;
	private ArrayList<Machine> availableMachines;
	private ArrayList<Thread> availableMachinesThreads;
	
	public UnitQueue(String Name) {
		this.Name = Name;
		this.productsQueue = new ArrayBlockingQueue<Product>(10);
		this.availableMachines = new ArrayList<Machine>();
		this.availableMachinesThreads = new ArrayList<Thread>();
	}
	
	public void addAvailableMachine(Machine m) {
		this.availableMachines.add(m);
	}
	
	public void addAvailableMachineThread(Thread t) {
		this.availableMachinesThreads.add(t);
	}
	
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
	
	public void Simulate() {
		// check available
		// check machine
		//for
		while (!this.productsQueue.isEmpty()) {
			/*while(this.productsQueue.poll() == null ) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}*/
			Product product = this.productsQueue.peek();
			if(product != null) {
				for(int i = 0 ; i < this.availableMachines.size() ; i++) {
					if(this.availableMachines.get(i).isAvalible()) {
						//synchronized(this.availableMachines.get(i)) {
							this.productsQueue.poll();
							//System.out.println("Ahmed" + product.getColor() );
							this.availableMachines.get(i).setProduct(product);
							//new Thread(this.availableMachines.get(i)).start();
							//this.availableMachinesThreads.add(new Thread(this.availableMachines.get(i)));
							//Thread t = this.availableMachinesThreads.get(i);
							//synchronized (this.availableMachines.get(i).getProduct()) {
							Thread t = new Thread(this.availableMachines.get(i));
							t.start();
							//}
							break;
						//}
					}
				}
			}
			
		}
		
	}

	@Override
	public String toString() {
		return "UnitQueue [Name=" + Name + ", productsQueue=" + productsQueue + ", availableMachines="
				+ availableMachines + "]";
	}

	public ArrayList<Thread> getAvailableMachinesThreads() {
		return availableMachinesThreads;
	}

	public void setAvailableMachinesThreads(ArrayList<Thread> availableMachinesThreads) {
		this.availableMachinesThreads = availableMachinesThreads;
	}

}
