package GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class myshapes {
	
	public double x = 50, y =50;
	private double Qwidth = 60, Qheight = 40;
	private double Mradius = 40;
	
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
	public Rectangle Qshape() {
		Rectangle Q = new Rectangle(x,y,Qwidth,Qheight);
		Q.setFill(Color.GOLD);;
		Q.setId("Q");
		return Q;
	}
	public Circle Mshape() {
		Circle M = new Circle(x,y,Mradius);
		M.setFill(Color.GRAY);
		M.setId("M");
		return M;
	}
	public Line drawLine(double[] lineCo, char[] shapeSympol) {
		lineCo[0] += x; lineCo[1] += y; lineCo[2] += x; lineCo[3] += y;
		if(shapeSympol[0] == 'Q') {
			lineCo[0] += Qwidth/2; lineCo[1] += Qheight/2;
		}
		if(shapeSympol[1] == 'Q') {
			lineCo[2] += Qwidth/2; lineCo[3] += Qheight/2;
		}
		return new Line(lineCo[0],lineCo[1],lineCo[2],lineCo[3]);
	}
	
}
