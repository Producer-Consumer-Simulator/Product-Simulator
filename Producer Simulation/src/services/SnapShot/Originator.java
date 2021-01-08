package services.SnapShot;

import models.Unit;

public class Originator {
	private Unit state;

	public Unit getState() {
		return state;
	}

	public void setState(Unit state) {
		this.state = state;
	}
	
	public Memento saveStateToMemonto(Unit state) {
		return new Memento(state);
	}
	
	public void  getStateFromMemento(Memento memento) {
		state = memento.getState();
	}
	
	
}
