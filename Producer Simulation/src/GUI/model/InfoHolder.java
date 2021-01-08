package GUI.model;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.TableView;

public class InfoHolder {
	public Group drawingArea;
	public myshapes shapes;
	public DecoShape root;
	public DecoShape[] twosides = new DecoShape[2];
	public TableView<Product> table;
	public ArrayList<Product> productInput = new ArrayList<Product>();
	//line
	double[] lineCo = new double[4];
	char[] shapeSympol = new char[2];
}
