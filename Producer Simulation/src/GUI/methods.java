package GUI;

import GUI.model.DecoShape;
import GUI.model.InfoHolder;
import javafx.scene.Cursor;
import javafx.scene.shape.Line;

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
		
		shape.getShape().setOnMousePressed(e -> {
			if(mode == 'D' && shape.dragable) {
			    // record a delta distance for the drag and drop operation.
			    dragDelta.x = shape.getShape().getLayoutX() - e.getSceneX();
			    dragDelta.y = shape.getShape().getLayoutY() - e.getSceneY();
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
		
		shape.getShape().setOnMouseReleased(e -> shape.getShape().setCursor(Cursor.HAND));
		
		shape.getShape().setOnMouseDragged(e -> {
			if(mode == 'D' && shape.dragable) {
				shape.getShape().setLayoutX(e.getSceneX() + dragDelta.x);
				shape.getShape().setLayoutY(e.getSceneY() + dragDelta.y);
			}
		});
		
		shape.getShape().setOnMouseEntered(e -> {
			shape.getShape().setCursor(Cursor.HAND);
			shape.getShape().setStrokeWidth(3);
		});
		
		shape.getShape().setOnMouseExited(e -> {
			shape.getShape().setCursor(Cursor.HAND);
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
				info.twosides[0].getShape().getLayoutX(),
				info.twosides[0].getShape().getLayoutY(),
				info.twosides[1].getShape().getLayoutX(),
				info.twosides[1].getShape().getLayoutY()
		};
		char[] ids = new char[] {
				info.twosides[0].getShape().getId().charAt(0),
				info.twosides[1].getShape().getId().charAt(0)
		};
		Line t = info.shapes.drawLine(atr, ids);
		info.drawingArea.getChildren().add(t);
		t.toBack();
	}
	
}
