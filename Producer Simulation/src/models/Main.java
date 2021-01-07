package models;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import services.UnitBuilder;

public class Main {

	public static void main(String[] args) {
		UnitBuilder ub = new UnitBuilder();
		ub.CreateMachine("ahmed", 5000, "q0", "q1");
		ub.CreateMachine("bbb", 10000, "q0", "q1");
		ub.CreateMachine("cccc", 20000, "q0", "q2");
		ub.CreateMachine("ddd", 25000, "q1", "q2");
		Unit u = ub.toUnit();
		BlockingQueue<Product> productsQueue = new ArrayBlockingQueue<Product>(10);
		productsQueue.add(new Product("uu", "yellow"));
		productsQueue.add(new Product("pp", "gray"));
		productsQueue.add(new Product("oo", "red"));
		productsQueue.add(new Product("vv", "green"));
		productsQueue.add(new Product("bb", "blue"));
		u.getQueue("q0").setProductsQueue(productsQueue);
		u.Simulate();
	}

}
