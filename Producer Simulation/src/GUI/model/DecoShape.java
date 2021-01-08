package GUI.model;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class DecoShape {
	
	private StackPane node;
	private Shape shape;
	private Text text;
	private char type;
	private ArrayList<Product> products;
	private ArrayList<DecoShape> next = new ArrayList<DecoShape>();
	private ArrayList<DecoShape> previous = new ArrayList<DecoShape>();
	public boolean dragable = true;
			
	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(Product product) {
		if(this.shape.getId().charAt(0) == 'Q')
			this.products.add(product);
		else {
			if(this.products.size() >= 1) {
				System.out.println("Machine can't handle more than one product at the same time");
				return;
			}
		}
	}
	
	public Text getText() {
		return text;
	}

	public void setText(String text) {
		this.text = new Text(text);
		node.getChildren().add(this.text);
		this.text.toFront();
	}

	public DecoShape() {
		node = new StackPane();
	}
	
	public StackPane getNode() {
		return node;
	}
	public void setNode(StackPane node) {
		this.node = node;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
		node.getChildren().add(shape);
		this.setText("");
		this.setType(shape.getId().charAt(0));
	}
	public Shape getShape(){
		return this.shape;
	}
	
	public boolean setNextShape(DecoShape newShape) {
		if(this.shape.getId().charAt(0) == 'M' && this.next.size() >= 1) {
			System.out.println("M should have one output only");
			return false;
		}
		this.next.add(newShape);
		return true;
	}
	public ArrayList<DecoShape> getNextShapes(){
		return this.next;
	}
	public void setPrevious(DecoShape pre) {
		if(this.shape.getId().charAt(0) == 'M' && this.previous.size() >= 1) {
			System.out.println("M should have one previous shape only");
		}
		this.previous.add(pre);
	}
	public ArrayList<DecoShape> getPrevious() {
		return this.previous;
	}
	
	public boolean search(DecoShape root, DecoShape findMe) {
		System.out.print("h");
		if(root == null) return false;
		else if(root == findMe) return true;
		else if(root.next.size() == 0) return false;
		else {
			boolean found = false;
			for(int i=0; i<root.next.size();) {
				found = search(root.getNextShapes().get(i++), findMe);
				if(found == true) break;
			}
			return found;
		}
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
}
