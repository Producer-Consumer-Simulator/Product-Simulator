package GUI;

import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

public class methods {
	
	public char mode = 'D';// 'D' for drag, 'L' for line
	
	class Delta { double x, y; }
	public void moveShape(Shape label) {
		
		final Delta dragDelta = new Delta();
		
		label.setOnMousePressed(e -> {
		    // record a delta distance for the drag and drop operation.
		    dragDelta.x = label.getLayoutX() - e.getSceneX();
		    dragDelta.y = label.getLayoutY() - e.getSceneY();
		});
		
		label.setOnMouseReleased(e ->  label.setCursor(Cursor.HAND));
		
		label.setOnMouseDragged(e -> {
		    label.setLayoutX(e.getSceneX() + dragDelta.x);
		    label.setLayoutY(e.getSceneY() + dragDelta.y);
		});
		
		label.setOnMouseEntered(e -> label.setCursor(Cursor.HAND));
	}
}
