package producerConsumerApp.services.SnapShot;

import producerConsumerApp.models.Unit;

public class Originator {
	private Unit state;

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
