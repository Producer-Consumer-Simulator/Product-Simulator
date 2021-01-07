package GUI;

import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class DecoShape {
	
	private Shape shape;
	private ArrayList<DecoShape> nextShapes = new ArrayList<DecoShape>();
	public boolean dragable = true;
	
	public void setShape(Shape shape) {
		this.shape = shape;
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
