package app.services.SnapShot;

import app.models.Unit;

public class Originator {

	private Originator() {

	}

	private Unit state;
	private static Originator instance;

	
	public Object Lock = new Object();
	public Object Lock2 = new Object();
	public Object FullLock = new Object();
	
	public static Originator getInstance() {
		if (instance == null) {
			instance = new Originator();
			instance.setState(new Unit());
		}
		return instance;
	}

	public Unit getState() {
		return state;
	}

	public void setState(Unit state) {
		this.state = state;
	}

	public Memento saveStateToMemonto(Unit state) {
		Unit s = state.Copy();
		return new Memento(s);
	}

	public void getStateFromMemento(Memento memento) {
		state = memento.getState();
	}

}
