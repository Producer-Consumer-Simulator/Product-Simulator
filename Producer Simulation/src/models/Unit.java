package models;

import java.util.ArrayList;

public class Unit {
	
	private ArrayList<UnitQueue> FactoryQueues ;
	private ArrayList<Machine> FactoryMachines ;
	
	public Unit() {
		FactoryQueues = new ArrayList<UnitQueue>();
		FactoryMachines = new ArrayList<Machine>();
	}
	
	public void addQueue(UnitQueue q) {
		FactoryQueues.add(q);
	}
	
	public void removeQueue (int index) {
		FactoryQueues.remove(index);
	}
	
	public int QueueSize() {
		return this.FactoryQueues.size();
	}
	
	public boolean isEmptyQueues() {
		return this.FactoryQueues.size() == 0;
	}
	
	public UnitQueue getQueue (String Name) {
		for (int i = 0 ; i < FactoryQueues.size();i++ ) {
			if (FactoryQueues.get(i).getName().equalsIgnoreCase(Name)) {
				return FactoryQueues.get(i);
			}
		}
		return null;
	}
	
	public void addMachine(Machine m) {
		FactoryMachines.add(m);
	}
	
	public void removeMachine (int index) {
		FactoryMachines.remove(index);
	}
	
	
	public void Simulate() {
		//get 1st queue
		//simulate
		if(this.FactoryQueues.get(0) != null) {
			this.FactoryQueues.get(0).Simulate();
		}
	}
}
