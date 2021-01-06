package SnapShot;

import models.Factory;

public class Memento {
	private Factory state;

	public Memento(Factory state) {
		this.state = state;
	}
	
	public Factory getState() {
		return state;
	}

	
}
