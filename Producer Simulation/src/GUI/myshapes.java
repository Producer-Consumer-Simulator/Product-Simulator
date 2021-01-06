package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class myshapes {
	
	public Button QButton;
	public Button MButton;
	public Button DrageButton;
	public Button LineButton;
	
	public Button AddQbutton() {
		QButton = new Button("Add Q");
		return QButton;
	}
	public Button AddMbutton() {
		MButton = new Button("Add M");
		return MButton;
	}
	public Button AddDrageButton() {
		DrageButton = new Button("Drag Mode");
		return DrageButton;
	}
	public Button AddLineButton() {
		LineButton = new Button("Line Mode");
		return LineButton;
	}
	
	
	public HBox separate(double height) {
		HBox sep = new HBox(new Separator(),new Line(0,0,0,height), new Separator());
		return sep;
	}
	public Rectangle Qshape(double x, double y) {
		Rectangle Q = new Rectangle(x,y,50,25);
		Q.setFill(Color.GOLD);
		return Q;
	}
	public Circle Mshape(double x, double y) {
		Circle M = new Circle(x,y,15);
		M.setFill(Color.GRAY);
		return M;
	}
	
}
