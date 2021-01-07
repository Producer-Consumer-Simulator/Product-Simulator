package GUI.model;

import javafx.scene.Group;

public class InfoHolder {
	public Group drawingArea;
	public myshapes shapes;
	public DecoShape root;
	public DecoShape[] twosides = new DecoShape[2];
	//line
	double[] lineCo = new double[4];
	char[] shapeSympol = new char[2];
}
