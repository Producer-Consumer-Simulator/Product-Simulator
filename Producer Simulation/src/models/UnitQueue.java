package models;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UnitQueue {
	

	private String Name;
	private BlockingQueue<Product> productsQueue;
	private ArrayList<Machine> availableMachines;
	
	public UnitQueue(String Name) {
		this.Name = Name;
		this.productsQueue = new ArrayBlockingQueue<Product>(10);
		this.availableMachines = new ArrayList<Machine>();
	}
	
	public void addAvailableMachine(Machine m) {
		this.availableMachines.add(m);
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
		for(;;) {
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
						this.productsQueue.poll();
						System.out.println("Ahmed" + product.getColor() );
						this.availableMachines.get(i).setProduct(product);
						new Thread(this.availableMachines.get(i)).start();
						break;
					}
				}
			}
			
		}
		
	}

}
