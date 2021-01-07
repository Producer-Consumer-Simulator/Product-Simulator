package models;

import java.util.ArrayDeque;
import java.util.Queue;

public class Machine {
	
	private Queue<Product> PrevQueue =  new ArrayDeque<Product>(10);
	private Queue<Product> nextQueue =  new ArrayDeque<Product>(10);

}
