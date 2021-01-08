package GUI;

import java.util.ArrayList;

import GUI.model.DecoShape;
import GUI.model.InfoHolder;
import GUI.model.Product;
import javafx.scene.Cursor;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class methods {
	
	private InfoHolder info;
	public char mode = 'D';// 'D' for drag, 'L' for line
	class Delta { double x, y; }
	boolean FirstNodeClicked = false;
	
	public methods(InfoHolder info) {
		this.info = info;
	}
	
	public void HandleShape(DecoShape shape) {
		
		final Delta dragDelta = new Delta();
		
		shape.getNode().setOnMousePressed(e -> {
			if(mode == 'D' && shape.dragable) {
			    // record a delta distance for the drag and drop operation.
			    dragDelta.x = shape.getNode().getLayoutX() - e.getSceneX();
			    dragDelta.y = shape.getNode().getLayoutY() - e.getSceneY();
			}
			else if(mode == 'L') {
				if(!FirstNodeClicked) {
					info.twosides[0] = shape;
					FirstNodeClicked = true;
				}
				else {
					info.twosides[1] = shape;
					FirstNodeClicked = false;
					if(connectUs()) {
						System.out.println("connected!");
						drawLine();						
					}
					else
						System.out.println("not connected!");
				}
			}
		});
		
		shape.getNode().setOnMouseReleased(e -> shape.getNode().setCursor(Cursor.HAND));
		
		shape.getNode().setOnMouseDragged(e -> {
			if(mode == 'D' && shape.dragable) {
				shape.getNode().setLayoutX(e.getSceneX() + dragDelta.x);
				shape.getNode().setLayoutY(e.getSceneY() + dragDelta.y);
			}
		});
		
		shape.getNode().setOnMouseEntered(e -> {
			shape.getNode().setCursor(Cursor.HAND);
			shape.getShape().setStrokeWidth(3);
		});
		
		shape.getNode().setOnMouseExited(e -> {
			shape.getNode().setCursor(Cursor.HAND);
			shape.getShape().setStrokeWidth(0);
		});
	}
	
	private boolean connectUs() {
		char id1 = info.twosides[0].getShape().getId().charAt(0);
		char id2 = info.twosides[1].getShape().getId().charAt(0);
		if(id1==id2) {
			System.out.println(id1+" to "+id1);
			return false;
		}
		else if(info.root == null) {
			System.out.println("not root");
			info.root = info.twosides[0];
			info.twosides[0].dragable = !info.twosides[0].setNextShape(info.twosides[1]);
			info.twosides[1].dragable = info.twosides[0].dragable;
			return !info.twosides[0].dragable;
		}
		else {
			DecoShape ds = new DecoShape(); 
			if(ds.search(info.root,info.twosides[0])) {
				System.out.println("\nshape found!");
				info.twosides[0].dragable = !info.twosides[0].setNextShape(info.twosides[1]);
				info.twosides[1].dragable = info.twosides[0].dragable;
				return !info.twosides[0].dragable;
			}
		}
		System.out.println("\nno path from root found");
		return false;
	}
	private void drawLine(){
		double[] atr = new double[] {
				info.twosides[0].getNode().getLayoutX() + info.twosides[0].getNode().getWidth()/2,
				info.twosides[0].getNode().getLayoutY() + info.twosides[0].getNode().getHeight()/2,
				info.twosides[1].getNode().getLayoutX() + info.twosides[1].getNode().getWidth()/2,
				info.twosides[1].getNode().getLayoutY() + info.twosides[1].getNode().getHeight()/2
		};
		//line
		Line t = new Line(atr[0],atr[1],atr[2],atr[3]); 
		info.drawingArea.getChildren().add(t);
		t.toBack();
		//some calculations
		double xDiff = atr[2]-atr[0], yDiff = atr[3]-atr[1];
		double length = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
		double angle = Math.atan(yDiff/xDiff);
		if(xDiff == 0) {
			angle = (yDiff>0)?Math.PI/2:Math.PI*3/2;
		}
		if(xDiff < 0)
			angle += Math.PI;
		double newX = atr[0]+((length/2)*Math.cos(angle))+5*Math.sin(angle);
		double newY = atr[1]+((length/2)*Math.sin(angle))-5*Math.cos(angle);
		//arrow
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(new Double[]{0.0,0.0,20.0,5.0,0.0,10.0});
		polygon.setLayoutX(newX); polygon.setLayoutY(newY);
		polygon.getTransforms().add(new Rotate((angle*180/Math.PI),0,0)); 
		info.drawingArea.getChildren().add(polygon);
		polygon.toBack();
	}
	private boolean fillTable(ArrayList<Product> products) {
		 
		return false;
	}
	
}
