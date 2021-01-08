package producerConsumerApp.services;

import producerConsumerApp.GUI.model.DecoShape;
import producerConsumerApp.models.Machine;
import producerConsumerApp.models.Unit;
import producerConsumerApp.models.UnitQueue;

public class UnitBuilder {
	
	private Unit OurUnit ;
	
	public UnitBuilder() {
		this.OurUnit = Unit.getInstance();
	}
	
	public UnitBuilder(Unit unit) {
		this.OurUnit = unit;
	}
	
	public void CreateMachine(DecoShape shape, String PrevQueueName ,String NextQueueName) {
		Machine m = new Machine(shape);
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
