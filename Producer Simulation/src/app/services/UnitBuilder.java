package app.services;

import app.GUI.model.DecoShape;
import app.models.Machine;
import app.models.Unit;
import app.models.UnitQueue;
import app.services.SnapShot.Originator;

public class UnitBuilder {
	
	private Unit OurUnit ;
	
	public UnitBuilder() {
		this.OurUnit = Originator.getInstance().getState();
	}
	
	public UnitBuilder(Unit unit) {
		this.OurUnit = unit;
	}
	
	public void CreateMachine(DecoShape shape,String Name,DecoShape PrevQueue ,DecoShape NextQueue) {
		Machine m = this.OurUnit.getMachine(Name);
		if (m == null) {
			m = new Machine(shape,Name);
		}
		UnitQueue prev = this.OurUnit.getQueue(PrevQueue.getTextString());
		if (prev == null) {
			prev = new UnitQueue(PrevQueue, PrevQueue.getTextString());
			addQueue(prev);
		}
		prev.addAvailableMachine(m);
		prev.lastQueue = false;
		UnitQueue next = this.OurUnit.getQueue(NextQueue.getTextString());
		if (next == null) {
			next = new UnitQueue(NextQueue, NextQueue.getTextString());
			next.lastQueue = true;
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
