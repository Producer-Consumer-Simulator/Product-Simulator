package producerConsumerApp.services.SnapShot;

import producerConsumerApp.models.Unit;

public class Memento {
	private Unit state;

	public Memento(Unit state) {
		this.state = state;
	}
	
	public Unit getState() {
		return state;
	}

	
}
