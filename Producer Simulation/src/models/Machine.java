package models;

//import java.util.ArrayDeque;
//import java.util.Queue;

public class Machine {
	
	private UnitQueue prevQueue ;
	private UnitQueue nextQueue ;
	private int time;
	private String Name;
	private Product product;
	
	public Machine(String Name,int time) {
		this.Name = Name;
		this.time = time;
	}

	public UnitQueue getPrevQueue() {
		return prevQueue;
	}

	public void setPrevQueue(UnitQueue prevQueue) {
		this.prevQueue = prevQueue;
	}

	public UnitQueue getNextQueue() {
		return nextQueue;
	}

	public void setNextQueue(UnitQueue nextQueue) {
		this.nextQueue = nextQueue;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
