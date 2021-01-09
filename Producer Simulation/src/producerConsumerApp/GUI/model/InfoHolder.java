package producerConsumerApp.GUI.model;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.TableView;
import producerConsumerApp.models.Product;

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
	public TableView<Product> table;
	public ArrayList<Product> productInput = new ArrayList<Product>();
	// line
	double[] lineCo = new double[4];
	char[] shapeSympol = new char[2];
}
