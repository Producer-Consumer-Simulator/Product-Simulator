package GUI;

import GUI.model.*;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainGUI extends Application{

	@Override
	public void start(Stage window) throws Exception {
		
		//variables
		double x = 1300, y = 650;
		myshapes shapes = new myshapes();
		shapes.x = 100; shapes.y = 100;
		Group drawingArea = new Group();
		InfoHolder info = new InfoHolder();
		info.drawingArea = drawingArea;
		info.shapes = shapes;
		methods method = new methods(info);
		
		//toolbar and its properties
		HBox toolbar = shapes.toolbar();
		toolbar.setLayoutX(0); toolbar.setLayoutY(0);
		
		//add action when button clicked
		shapes.QButton.setOnAction(e -> {
			//create shape, add listener to move it, add it to drawingArea
			DecoShape t = new DecoShape();
			t.setShape(shapes.Qshape());
			method.HandleShape(t);
			drawingArea.getChildren().add(t.getShape());
		});
		shapes.MButton.setOnAction(e -> {
			DecoShape t = new DecoShape();
			t.setShape(shapes.Mshape());
			method.HandleShape(t);
			drawingArea.getChildren().add(t.getShape());
		});
		shapes.DrageButton.setOnAction(e ->{
			method.mode = 'D';
			method.FirstNodeClicked = false;
		});
		shapes.LineButton.setOnAction(e ->{
			method.mode = 'L';
		});
		shapes.addproduct.setOnAction(e ->{
			System.out.println("person added!");
		});
		
		
		Group container = new Group();
		container.getChildren().addAll(toolbar,drawingArea);
		
		Scene scene = new Scene(container,x,y);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(scene);
		window.show();
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
