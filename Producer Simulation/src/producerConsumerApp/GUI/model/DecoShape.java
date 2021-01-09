package producerConsumerApp.GUI.model;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import producerConsumerApp.models.Product;

public class DecoShape {

	public boolean visited = false;
	private StackPane node;
	private Shape shape;
	private Text text = new Text();
	private char type;
	private ArrayList<Product> products;
	private ArrayList<DecoShape> next = new ArrayList<DecoShape>();
	private ArrayList<DecoShape> previous = new ArrayList<DecoShape>();
	public boolean dragable = true;

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(Product product) {
		if (this.shape.getId().charAt(0) == 'Q')
			this.products.add(product);
		else {
			if (this.products.size() >= 1) {
				System.out.println("Machine can't handle more than one product at the same time");
				return;
			}
		}
	}

	public Text getText() {
		return this.text;
	}

	public String getTextString() {
		return this.text.getText();
	}

	public void setText(String text) {
		if (this.text == null)
			System.out.println("no text object found!");
		this.text.setText(text);
	}

	public void setText(Text text) {
		this.text = text;
		node.getChildren().add(text);
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
		this.setType(shape.getId().charAt(0));
	}

	public Shape getShape() {
		return this.shape;
	}

	public boolean setNext(DecoShape newShape) {
		if (this.shape.getId().charAt(0) == 'M' && this.next.size() >= 1) {
			System.out.println("M should have one output only");
			return false;
		}
		this.next.add(newShape);
		return true;
	}

	public ArrayList<DecoShape> getNext() {
		return this.next;
	}

	public void setPrevious(DecoShape pre) {
		if (this.shape.getId().charAt(0) == 'M' && this.previous.size() >= 1) {
			System.out.println("M should have one previous shape only");
		}
		this.previous.add(pre);
	}

	public ArrayList<DecoShape> getPrevious() {
		return this.previous;
	}

	public boolean search(DecoShape root, DecoShape findMe) {
		System.out.print("h");
		if (root == null)
			return false;
		else if (root == findMe)
			return true;
		else if (root.next.size() == 0)
			return false;
		else {
			boolean found = false;
			for (int i = 0; i < root.next.size();) {
				found = search(root.getNext().get(i++), findMe);
				if (found == true)
					break;
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

	public void setColor(Color color) {
		this.shape.setFill(color);
	}
}
