package app.models;

import java.util.ArrayList;

public class Unit {
	private ArrayList<UnitQueue> FactoryQueues;
	private ArrayList<Machine> FactoryMachines;
	private ArrayList<Product> FinishedProducts;

	

	public Unit() {
		FactoryQueues = new ArrayList<UnitQueue>();
		FactoryMachines = new ArrayList<Machine>();
		setFinishedProducts(new ArrayList<Product>());
	}

	public ArrayList<UnitQueue> getfactorqueue() {
		return FactoryQueues;
	}

	@SuppressWarnings("unchecked")
	public Unit Copy() {
		Unit s = new Unit();
		s.FactoryQueues = (ArrayList<UnitQueue>) this.FactoryQueues.clone();
		s.FactoryMachines = (ArrayList<Machine>) this.FactoryMachines.clone();
		return s;
	}

	public void addQueue(UnitQueue q) {
		FactoryQueues.add(q);
	}

	public UnitQueue getFirstQueue() {
		return FactoryQueues.get(0);
	}

	public void removeQueue(int index) {
		FactoryQueues.remove(index);
	}

	public int QueueSize() {
		return this.FactoryQueues.size();
	}

	public boolean isEmptyQueues() {
		return this.FactoryQueues.size() == 0;
	}

	public UnitQueue getQueue(String Name) {
		for (int i = 0; i < FactoryQueues.size(); i++) {
			if (FactoryQueues.get(i).getName().equalsIgnoreCase(Name)) {
				return FactoryQueues.get(i);
			}
		}
		return null;
	}

	public Machine getMachine(String Name) {
		for (int i = 0; i < FactoryMachines.size(); i++) {
			if (FactoryMachines.get(i).getName().equalsIgnoreCase(Name)) {
				return FactoryMachines.get(i);
			}
		}
		return null;
	}

	public void addMachine(Machine m) {
		FactoryMachines.add(m);
	}

	public void removeMachine(int index) {
		FactoryMachines.remove(index);
	}

	public boolean addProduct(Product p) {
		if (!FactoryQueues.isEmpty()) {
			FactoryQueues.get(0).addProduct(p);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Unit [FactoryQueues=" + FactoryQueues + ", FactoryMachines=" + FactoryMachines + "]";
	}

	public ArrayList<Product> getFinishedProducts() {
		return FinishedProducts;
	}

	public void setFinishedProducts(ArrayList<Product> finishedProducts) {
		FinishedProducts = finishedProducts;
	}

}
