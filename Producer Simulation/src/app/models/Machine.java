package app.models;

import java.util.Random;

import app.GUI.model.DecoShape;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class Machine implements Runnable {

	private UnitQueue nextQueue;
	private long time;
	private String Name;
	private Product product;
	private DecoShape guiShape;
	private boolean avalible = true;

	private Object Lock;
	private Object ThreadLock;

	public Machine(DecoShape shape, String Name) {
		this.Name = Name;
		this.guiShape = shape;
		Random r = new Random();
		this.time = 5000 + r.nextInt(30000);
	}

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

	public void StartMachine(Product product, Object Lock) {
		this.product = product;
		this.ThreadLock = Lock;
		this.avalible = false;
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("Machine " + this.guiShape.getTextString() + " starts " + this.product.getFirstName()
				+ " time: " + this.time);
		if (this.Lock != null) {
			runInstead(this/* .Lock */);
		} else if (this.ThreadLock != null) {
			runInstead(this.ThreadLock);
		}
	}

	private void runInstead(Object Lock) {
		long startTime = System.currentTimeMillis();
		synchronized (Lock) {
			try {
				this.guiShape.setColor(this.product.getFxcolor());
				showRemainingTime(startTime, Lock);
				while (this.nextQueue.isFullProductQueue()) {
					Lock.wait(1000);
				}
				this.guiShape.setText(this.Name);
				System.out.println("Machine " + this.guiShape.getTextString() + " ends " + this.product.getFirstName());
				this.guiShape.setColor(Color.GRAY);
				this.avalible = true;
				this.nextQueue.StartQueue(this.product);
				if (this.Lock != null) {
					synchronized (this.Lock) {
						this.Lock.notify();
					}
				}
				if (ThreadLock != null) {
					synchronized (ThreadLock) {
						this.ThreadLock.notifyAll();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void showRemainingTime(long StartTime, Object Lock) {
		synchronized (Lock) {
			try {
				long t = this.time - (System.currentTimeMillis() - StartTime);
				while (t > 0) {
					String ti = Long.toString(t / 1000);
					//System.out.println(ti);
					Platform.runLater(() -> {
						this.guiShape.setText(ti);
					});
					Lock.wait(1000);
					t = this.time - (System.currentTimeMillis() - StartTime);
				}
			} catch (InterruptedException e) {
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

}