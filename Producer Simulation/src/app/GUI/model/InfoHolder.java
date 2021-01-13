package app.GUI.model;

import java.util.ArrayList;

import app.models.Product;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

public class InfoHolder {

	private static InfoHolder instance;

	private InfoHolder() {
	}

	public static InfoHolder getInstance() {
		if (instance == null)
			instance = new InfoHolder();
		return instance;
	}

	public Group drawingArea;
	public myshapes shapes;
	public DecoShape root;
	public int QCounter = 1;
	public int Mcounter = 1;
	public DecoShape[] twosides = new DecoShape[2];
	public ListView<StackPane> productsGraphI;
	public ListView<StackPane> productsGraphO;
	public ListView<StackPane> productsQueue;
	public ArrayList<Product> productInput = new ArrayList<Product>();
	// line
	double[] lineCo = new double[4];
	char[] shapeSympol = new char[2];
}
