package app.GUI;

import java.util.ArrayList;

import app.GUI.model.DecoShape;
import app.GUI.model.InfoHolder;
import app.GUI.model.myshapes;
import app.services.*;
import app.models.Product;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class mainGUI extends Application {

	private static String[] args;
	public ArrayList<String> queue = new ArrayList<String>();
	private boolean runcheck =false;
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
		//input
		VBox productI = shapes.productList("Input");
		info.productsGraphI = shapes.tempList;
		//output
		VBox productO = shapes.productList("Output");
		info.productsGraphO = shapes.tempList;
		//Queue
		VBox productQ = shapes.productList("Queue");
		info.productsQueue = shapes.tempList;
		//product input/output/queue holder
		HBox productsInfo = shapes.productInfo(productI, productO, productQ);
		productsInfo.setLayoutX(840); productsInfo.setLayoutY(340);

		info.drawingArea = drawingArea;
		info.shapes = shapes;
		methods method = methods.getInstance();

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
			runcheck=true;
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
			shapes.textField.setText("");
			if (!text.equals(textNull)) {
				Product p = new Product(text);
				info.productInput.add(p);
				method.addproductGrpah(p, 'I');
				if(runcheck) {
					ProducerConsumer pc = ProducerConsumer.getInstance();
					Thread t1 = new Thread(new Runnable() { 
			            @Override
			            public void run() 
			            { 
			            	pc.produce(p);
			            } 
			        });
					t1.start();		
				}		
			}
		});
		shapes.reset.setOnMouseClicked(e ->{
			runcheck=false;
			drawingArea.getChildren().clear();
			info.productInput = new ArrayList<Product>();
			info.productsGraphI.getItems().clear();
			info.productsGraphO.getItems().clear();
			info.productsQueue.getItems().clear();
			info.root = null;
			info.QCounter = 1;
			info.Mcounter = 1;
		});

		textNull = shapes.textField.getText();
		Group container = new Group();
		container.getChildren().addAll(toolbar, drawingArea, productsInfo);
		toolbar.toFront(); productsInfo.toFront();
		
		Scene scene = new Scene(container, x, y);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

	public static void start() {
		launch(args);
	}


}
