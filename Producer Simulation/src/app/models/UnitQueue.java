package app.models;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import app.GUI.methods;
import app.GUI.model.DecoShape;
import app.services.SnapShot.Originator;

public class UnitQueue implements Runnable {

	private String Name;
	private BlockingQueue<Product> productsQueue;
	private ArrayList<Machine> availableMachines;
	private DecoShape guiShape;
	public boolean lastQueue = false;

	private Object Lock = new Object();

	public UnitQueue(DecoShape shape, String Name) {
		this.guiShape = shape;
		this.Name = Name;
		this.productsQueue = new ArrayBlockingQueue<Product>(10);
		this.availableMachines = new ArrayList<Machine>();
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
		this.guiShape.setProducts(p);
	}

	public void StartQueue(Product p) {
		this.productsQueue.add(p);
		this.guiShape.setProducts(p);
		new Thread(this).start();
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

	public boolean isFullProductQueue() {
		return this.productsQueue.size() >= 10;
	}

	public void Simulate(Product p) {
		synchronized (Lock) {
			Machine available = getAvailableMachine();
			while (available == null) {
				try {
					System.out.println("Simulate is wait");
					this.Lock.wait();
					System.out.println("Simulate is Not wait");
					available = getAvailableMachine();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			available.StartMachine(p, Lock);
			this.guiShape.removeFirstProduct();
		}

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
		while (!this.productsQueue.isEmpty()) {
			synchronized (productsQueue) {
				if (this.lastQueue) {
					for (int i = 0; i < this.productsQueue.size(); i++) {
						methods.getInstance().addproductGrpah(this.productsQueue.peek(), 'O');
						Originator.getInstance().getState().getFinishedProducts().add(this.productsQueue.poll());
						this.guiShape.removeFirstProduct();
					}
					System.out.println(Originator.getInstance().getState().getFinishedProducts());
				} else {
					Product product = this.productsQueue.poll();
					if (product != null) {
						synchronized (Originator.getInstance().Lock) {
							Machine available = getAvailableMachine();
							while (available == null) {
								try {
									System.out.println("thread queue " + this.Name + " wait");
									Originator.getInstance().Lock.wait();
									System.out.println("thread queue " + this.Name + "NOt wait");
									available = getAvailableMachine();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							available.StartMachine(product, Originator.getInstance().Lock);
							this.guiShape.removeFirstProduct();
						}

					}
				}
			}
		}
	}
}