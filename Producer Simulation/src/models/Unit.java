package models;

import java.util.ArrayList;
import java.util.Queue;
public class Unit {
	
	private ArrayList<Queue<Product>> FactoryQueues ;
	private ArrayList<Machine> FactoryMachines ;
	
	public Unit() {
		FactoryQueues = new ArrayList<Queue<Product>>();
		FactoryMachines = new ArrayList<Machine>();
	}
	
	public void addQueue(Queue<Product> q) {
		FactoryQueues.add(q);
	}
	
	public void removeQueue (int index) {
		FactoryQueues.remove(index);
	}
	
	public int QueueSize() {
		return this.FactoryQueues.size();
	}
	
	public boolean isEmptyQueue() {
		return this.FactoryQueues.size() == 0;
	}
	
	public void addMachine(Machine m) {
		FactoryMachines.add(m);
	}
	
	public void removeMachine (int index) {
		FactoryMachines.remove(index);
	}
}
