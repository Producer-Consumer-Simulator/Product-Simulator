package models;

public class Machine implements Runnable {
	
	//private UnitQueue prevQueue ;
	private UnitQueue nextQueue ;
	private int time;
	private String Name;
	private Product product;
	private boolean avalible = true ;
	public Machine(String Name,int time) {
		this.Name = Name;
		this.time = time;
	}

	/*public UnitQueue getPrevQueue() {
		return prevQueue;
	}

	public void setPrevQueue(UnitQueue prevQueue) {
		this.prevQueue = prevQueue;
	}*/

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
	private static Object LOCK = new Object();
	@Override
	public void run() {
		//set color
		//wait
		//set color
		//queue simulate
		
		synchronized (LOCK) {
		try {
			this.avalible = false;
			System.out.println(this.product.getColor());
			this.wait(this.time);
			this.setProduct(new Product("lllll", "qqqqq"));
			System.out.println("Black");
			//this.nextQueue.Simulate();
			this.avalible = true ;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
		
	}

	public boolean isAvalible() {
		return avalible;
	}

}
