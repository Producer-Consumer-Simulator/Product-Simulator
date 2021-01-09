package producerConsumerApp.GUI;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import producerConsumerApp.GUI.model.DecoShape;
import producerConsumerApp.GUI.model.InfoHolder;
import producerConsumerApp.GUI.model.myshapes;
import producerConsumerApp.models.Product;

public class mainGUI extends Application implements Runnable {

	private String[] args;
	private boolean check = false;
	public ArrayList<String> queue = new ArrayList<String>();
	String textNull = "\n";

	@Override
	public void start(Stage window) throws Exception {

		// variables
		double x = 1300, y = 650;
		myshapes shapes = new myshapes();
		shapes.x = 100;
		shapes.y = 100;
		Group drawingArea = new Group();
		InfoHolder info = InfoHolder.getInstance();
		// table
		info.table = new TableView<Product>();
		// text null

		info.drawingArea = drawingArea;
		info.shapes = shapes;
		methods method = new methods(info);

		// toolbar and its properties
		HBox toolbar = shapes.toolbar();
		toolbar.setLayoutX(0);
		toolbar.setLayoutY(0);

		// add action when button clicked
		shapes.QButton.setOnAction(e -> {
			// create shape, add listener to move it, add it to drawingArea
			DecoShape t = new DecoShape();
			shapes.SetStackPane(t.getNode());
			t.setShape(shapes.Qshape());
			t.setText(new Text("Q" + String.valueOf(info.QCounter++)));
			method.HandleShape(t);
			drawingArea.getChildren().add(t.getNode());
		});
		shapes.MButton.setOnAction(e -> {
			DecoShape t = new DecoShape();
			shapes.SetStackPane(t.getNode());
			t.setShape(shapes.Mshape());
			t.setText(new Text("M" + String.valueOf(info.Mcounter++)));
			method.HandleShape(t);
			drawingArea.getChildren().add(t.getNode());
		});
		shapes.run.setOnAction(e -> {
			methods.Simulate();
		});
		shapes.DrageButton.setOnAction(e -> {
			method.mode = 'D';
			shapes.DrageButton.setStyle("-fx-cursor:hand;\r\n" + "	-fx-border-color:#2196f3;\r\n"
					+ "	-fx-border-width:3px;\r\n" + "	-fx-border-radius:20px;\r\n"
					+ "	-fx-background-color:#FFF;\r\n" + "	-fx-text-fill:#2196f3;\r\n" + "	-fx-font-weight:bold;");
			shapes.LineButton.setStyle("-fx-background-color:#2196f3;\r\n" + "	-fx-font-family:Tahoma;\r\n"
					+ "	-fx-font-size:15px;\r\n" + "	-fx-text-fill:#FFF;\r\n" + "	-fx-background-radius:20px;\r\n"
					+ "	-fx-font-weight:bold;");

			method.FirstNodeClicked = false;
		});
		shapes.LineButton.setOnAction(e -> {
			shapes.LineButton.setStyle("-fx-cursor:hand;\r\n" + "	-fx-border-color:#2196f3;\r\n"
					+ "	-fx-border-width:3px;\r\n" + "	-fx-border-radius:20px;\r\n"
					+ "	-fx-background-color:#FFF;\r\n" + "	-fx-text-fill:#2196f3;\r\n" + "	-fx-font-weight:bold;");
			shapes.DrageButton.setStyle("-fx-background-color:#2196f3;\r\n" + "	-fx-font-family:Tahoma;\r\n"
					+ "	-fx-font-size:15px;\r\n" + "	-fx-text-fill:#FFF;\r\n" + "	-fx-background-radius:20px;\r\n"
					+ "	-fx-font-weight:bold;");
			method.mode = 'L';
		});
		shapes.addproduct.setOnMouseClicked(e -> {
			String text = shapes.textField.getText();
			if (!text.equals(textNull)) {
				info.productInput.add(new Product(text));
				method.fillTable(info.productInput);
				Thread t = new Thread(new Producer(text));
				t.start();
			}
		});

		textNull = shapes.textField.getText();
		method.fillTable(info.productInput);
		Group container = new Group();
		container.getChildren().addAll(toolbar, drawingArea, info.table);

		Scene scene = new Scene(container, x, y);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		launch(args);
	}

}
