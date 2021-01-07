package models;

import java.util.ArrayList;
import java.util.Queue;

public class UnitQueue {
	

	private String Name;
	private Queue<Product> productsQueue;
	private ArrayList<Machine> availableMachines;
	
	public UnitQueue(String Name) {
		this.Name = Name;
	}
	
	public void addAvailableMachine(Machine m) {
		this.availableMachines.add(m);
	}
	
	public Queue<Product> getProductsQueue() {
		return productsQueue;
	}
	public void setProductsQueue(Queue<Product> productsQueue) {
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
		
	}

}
