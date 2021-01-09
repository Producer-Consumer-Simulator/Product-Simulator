package producerConsumerApp.models;

import java.util.ArrayList;

import producerConsumerApp.GUI.model.DecoShape;
import producerConsumerApp.services.UnitBuilder;

public class Unit {
	private static Unit instance;
	private ArrayList<UnitQueue> FactoryQueues ;
	private ArrayList<Machine> FactoryMachines ;
	
	private Unit() {
		FactoryQueues = new ArrayList<UnitQueue>();
		FactoryMachines = new ArrayList<Machine>();
	}
	
	public static Unit getInstance() {
		if(instance == null)
			instance = new Unit();
		return instance;
	}
	public ArrayList<UnitQueue> getfactorqueue(){
		return FactoryQueues;
	}
	
	@SuppressWarnings("unchecked")
	public Unit Copy () {
		Unit s = new Unit();
		s.FactoryQueues = (ArrayList<UnitQueue>) this.FactoryQueues.clone();
		s.FactoryMachines = (ArrayList<Machine>) this.FactoryMachines.clone();
		return s;
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
	
	public boolean addProduct(Product p) {
		if (!FactoryQueues.isEmpty()) {
			FactoryQueues.get(0).addProduct(p);
			return true;
		}
		return false;
	}
	
	
	public void CreateMachine(DecoShape shape, String PrevQueueName ,String NextQueueName) {
		UnitBuilder b = new UnitBuilder(this);
		b.CreateMachine(shape, PrevQueueName, NextQueueName);
	}
	
	
	
	public void Simulate() {
		//get 1st queue
		//simulate
		if(this.FactoryQueues.get(0) != null) {
			this.FactoryQueues.get(0).Simulate();
		}
	}

	@Override
	public String toString() {
		return "Unit [FactoryQueues=" + FactoryQueues + ", FactoryMachines=" + FactoryMachines + "]";
	}
	
	
}
