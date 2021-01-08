package producerConsumerApp.models;

import java.util.Random;

import javafx.scene.paint.Color;
import producerConsumerApp.GUI.model.DecoShape;

public class Machine implements Runnable {
	
	//private UnitQueue prevQueue ;
	private UnitQueue nextQueue ;
	private long time;
	//private String Name;
	private Product product;
	private DecoShape guiShape;
	private boolean avalible = true ;
	
	
	public Machine(DecoShape shape) {
		this.guiShape = shape;
		Random r = new Random();
		this.time = 10000 + r.nextInt(10000);
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

	public long getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	/*public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}*/

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	//public static Object LOCK = new Object();
	/*@SuppressWarnings("deprecation")
	@Override
	public void run() {
		//set color
		//wait
		//set color
		//queue simulate
		
		//synchronized (this) {
		try {
			//this.avalible = false;
			//this.product = this.prevQueue.getProductsQueue().poll();
			long startTime = System.nanoTime();
			//System.out.println(this.Name);
			System.out.println(this.Name+" "+this.product.getColor());
			//this.wait(this.time);
			while ((System.nanoTime()-startTime)<this.time) {
				
			}
			//wait(this.time);
			//this.setProduct(new Product("lllll", "qqqqq"));
			//System.out.println("Black");
			System.out.println("End " + this.Name+" "+this.product.getColor());
			this.prevQueue.getAvailableMachines().add(this);
			this.nextQueue.getProductsQueue().add(this.product);
			
			this.nextQueue.Simulate();
			//this.avalible = true ;
			Thread t = Thread.currentThread();
			t.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//}
		
	}*/

	@Override
	public void run() {
		//long startTime = System.currentTimeMillis();
		synchronized(this) {
			try {
			System.out.println("Machine memory: "+this);
			this.guiShape.setColor(this.product.fxcolor);
			this.guiShape.setText(this.product.getFirstName()+" "+this.time/1000);
			System.out.println(/*this.Name+*/" "+this.product.getColor());
			//this.guiShape.getShape().setStyle("-fx-background-color:"+this.product.getColor()+";");
			/*while (this.time-(System.currentTimeMillis()-startTime)>0) {
				System.out.println("remaining time : " + (this.time-(System.currentTimeMillis()-startTime)) );
			}*/
			wait(this.time);
			System.out.println("End " + /*this.Name+" "+*/this.product.getColor());
			this.nextQueue.getProductsQueue().add(this.product);	
			this.nextQueue.Simulate();
			this.avalible = true ;
			this.guiShape.setColor(Color.GRAY);
			this.guiShape.setText(" ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public boolean isAvalible() {
		return avalible;
	}
	
	public void setAvailable(boolean b) {
		this.avalible=b;
	}

	/*@Override
	public String toString() {
		return "Machine [nextQueue=" + nextQueue + ", time=" + time + ", product=" + product + ", guiShape=" + guiShape
				+ ", avalible=" + avalible + "]";
	}*/

	

}
