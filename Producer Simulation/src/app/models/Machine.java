package app.models;

import java.util.Random;

import app.GUI.model.DecoShape;
import javafx.scene.paint.Color;

public class Machine implements Runnable {

	// private UnitQueue prevQueue ;
	private UnitQueue nextQueue;
	private long time;
	private String Name;
	private Product product;
	private DecoShape guiShape;
	private boolean avalible = true;
	
	private Object Lock;
	private Object ThreadLock ;

	public Machine(DecoShape shape, String Name) {
		this.Name = Name;
		this.guiShape = shape;
		Random r = new Random();
		this.time = 5000 + r.nextInt(30000);
	}

	/*
	 * public UnitQueue getPrevQueue() { return prevQueue; }
	 * 
	 * public void setPrevQueue(UnitQueue prevQueue) { this.prevQueue = prevQueue; }
	 */

	public UnitQueue getNextQueue() {
		return nextQueue;
	}

	public void setNextQueue(UnitQueue nextQueue) {
		this.nextQueue = nextQueue;
	}

	public long getTime() {
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
	// public static Object LOCK = new Object();
	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Override public void run() { //set color //wait //set color //queue simulate
	 * 
	 * //synchronized (this) { try { //this.avalible = false; //this.product =
	 * this.prevQueue.getProductsQueue().poll(); long startTime = System.nanoTime();
	 * //System.out.println(this.Name);
	 * System.out.println(this.Name+" "+this.product.getColor());
	 * //this.wait(this.time); while ((System.nanoTime()-startTime)<this.time) {
	 * 
	 * } //wait(this.time); //this.setProduct(new Product("lllll", "qqqqq"));
	 * //System.out.println("Black"); System.out.println("End " +
	 * this.Name+" "+this.product.getColor());
	 * this.prevQueue.getAvailableMachines().add(this);
	 * this.nextQueue.getProductsQueue().add(this.product);
	 * 
	 * this.nextQueue.Simulate(); //this.avalible = true ; Thread t =
	 * Thread.currentThread(); t.stop(); } catch (Exception e) {
	 * e.printStackTrace(); } //}
	 * 
	 * }
	 */

	@Override
	public void run() {
		System.out.println("Machine " + this.guiShape.getTextString() + " starts " + this.product.getFirstName() + " time: " +this.time);
		//long startTime = System.currentTimeMillis();
		if(this.Lock != null) {
			System.out.println("first queue lock");
			runinstead(this/*.Lock*/);
		}
		else if(this.ThreadLock != null) {
			System.out.println("thread queue lock");
			runinstead(this.ThreadLock);
		}
		/*else {
			System.out.println("thththth queue lock");
			runinstead(this);
		}*/
		

	}
	
	private void runinstead (Object Lock) {
		long startTime = System.currentTimeMillis();
		synchronized (Lock) {
			try {
				this.guiShape.setColor(this.product.getFxcolor());
				// System.out.println(this.product.getFirstName()+" "+this.product.getColor());
				// this.guiShape.getShape().setStyle("-fx-background-color:"+this.product.getColor()+";");
				  long t = this.time-(System.currentTimeMillis()-startTime);
				  while (t>0) {
					  String ti = Long.toString(t/1000);
					  System.out.println(ti);
					  this.guiShape.setText(ti);
					  Lock.wait(1000);
					  t =this.time-(System.currentTimeMillis()-startTime); 
				  }
				//Lock.wait(this.time);
				// System.out.println("End " + /*this.Name+" "+*/this.product.getFirstName());
				while (this.nextQueue.isFullProductQueue()) {Lock.wait(1000);}
				this.nextQueue.getProductsQueue().add(this.product);
				// this.guiShape.setText(" ");
				System.out.println("Machine " + this.guiShape.getTextString() + " ends " + this.product.getFirstName());
				this.guiShape.setColor(Color.GRAY);
				//Lock.wait(50);
				this.avalible = true;
				new Thread(this.nextQueue).start();
				/*synchronized (nextQueue) {
					new Thread(this.nextQueue).start();
				}*/
				//Lock.notifyAll();
				if (this.Lock!=null) {
					synchronized (this.Lock) {
						this.Lock.notify();
					}
				}
				if (ThreadLock!=null) {
					synchronized (ThreadLock) {
						this.ThreadLock.notifyAll();
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public boolean isAvalible() {
		return avalible;
	}

	public void setAvailable(boolean b) {
		this.avalible = b;
	}

	public void setLock(Object lock) {
		Lock = lock;
	}

	public void setThreadLock(Object threadLock) {
		ThreadLock = threadLock;
	}

	/*
	 * @Override public String toString() { return "Machine [nextQueue=" + nextQueue
	 * + ", time=" + time + ", product=" + product + ", guiShape=" + guiShape +
	 * ", avalible=" + avalible + "]"; }
	 */

}