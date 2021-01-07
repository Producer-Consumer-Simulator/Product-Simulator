package services;

import models.Unit;
import models.UnitQueue;
import models.Machine;

public class UnitBuilder {
	
	private Unit OurUnit ;
	
	public UnitBuilder() {
		this.OurUnit = new Unit();
	}
	
	
	public void CreateMachine(String MachineName , int MachineTime , String PrevQueueName ,String NextQueueName) {
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
			addQueue(next);
		}
		m.setPrevQueue(prev);
		m.setNextQueue(next);
		addMachine(m);
	}
	
	public void addMachine (Machine m) {
		this.OurUnit.addMachine(m);
	}
	
	public void addQueue(UnitQueue q) {
		this.addQueue(q);
	}
	
	public Unit toUnit() {
		return this.OurUnit;
	}

}
