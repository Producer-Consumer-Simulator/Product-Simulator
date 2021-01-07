package GUI.model;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class DecoShape {
	
	private StackPane node;
	private Shape shape;
	private Text text;
	
	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
		node.getChildren().add(text);
		text.toFront();
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

	private ArrayList<DecoShape> nextShapes = new ArrayList<DecoShape>();
	public boolean dragable = true;
	
	public void setShape(Shape shape) {
		this.shape = shape;
		node.getChildren().add(shape);
		this.setText(new Text("any"));
	}
	public Shape getShape(){
		return this.shape;
	}
	
	public boolean setNextShape(DecoShape newShape) {
		if(this.shape.getId().charAt(0) == 'M' && this.nextShapes.size() >= 1) {
			System.out.println("M should have one output only");
			return false;
		}
		this.nextShapes.add(newShape);
		return true;
	}
	public ArrayList<DecoShape> getNextShapes(){
		return this.nextShapes;
	}
	
	public boolean search(DecoShape root, DecoShape findMe) {
		System.out.print("h");
		if(root == null) return false;
		else if(root == findMe) return true;
		else if(root.nextShapes.size() == 0) return false;
		else {
			boolean found = false;
			for(int i=0; i<root.nextShapes.size();) {
				found = search(root.getNextShapes().get(i++), findMe);
				if(found == true) break;
			}
			return found;
		}
	}
	
}
