package services;

import models.Unit;
import models.UnitQueue;
import models.Machine;

public class UnitBuilder {
	
	private Unit OurUnit ;
	
	public UnitBuilder() {
		this.OurUnit = new Unit();
	}
	
	public UnitBuilder(Unit unit) {
		this.OurUnit = unit;
	}
	
	public void CreateMachine(String MachineName , long MachineTime , String PrevQueueName ,String NextQueueName) {
		Machine m = new Machine(MachineName,MachineTime);
		UnitQueue prev = this.OurUnit.getQueue(PrevQueueName);
		if (prev == null) {
			prev = new UnitQueue(PrevQueueName);
			addQueue(prev);
		}
		prev.addAvailableMachine(m);
		UnitQueue next = this.OurUnit.getQueue(NextQueueName);
		if (next == null) {
			next = new UnitQueue(NextQueueName);
			next.lastQueue = true;
			prev.lastQueue = false;
			addQueue(next);
		}
		m.setNextQueue(next);
		addMachine(m);
	}
	
	public void addMachine (Machine m) {
		this.OurUnit.addMachine(m);
	}
	
	public void addQueue(UnitQueue q) {
		this.OurUnit.addQueue(q);
	}
	
	public Unit toUnit() {
		return this.OurUnit;
	}


	@Override
	public String toString() {
		return "UnitBuilder [OurUnit=" + OurUnit + "]";
	}
	

}
