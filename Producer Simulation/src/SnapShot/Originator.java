package SnapShot;

import models.Factory;

public class Originator {
	private Factory state;

	public Factory getState() {
		return state;
	}

	public void setState(Factory state) {
		this.state = state;
	}
	
	public Memento saveStateToMemonto(Factory state) {
		return new Memento(state);
	}
	
	public void  getStateFromMemento(Memento memento) {
		state = memento.getState();
	}
	
	
}
