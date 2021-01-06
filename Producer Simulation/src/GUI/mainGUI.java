package GUI;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainGUI extends Application{

	@Override
	public void start(Stage window) throws Exception {
		
		//variables
		myshapes shapes = new myshapes();
		methods method = new methods();
		Group drawingArea = new Group();
		double x = 1300, y = 650;
		
		//toolbar and its properties
		HBox toolbar = new HBox();
		double height = 30;
		toolbar.setLayoutX(0); toolbar.setLayoutY(0); toolbar.prefHeight(height);
		toolbar.getChildren().addAll(shapes.AddQbutton(), shapes.separate(height), shapes.AddMbutton(),shapes.separate(height));
		toolbar.getChildren().addAll(shapes.AddDrageButton(), shapes.separate(height), shapes.AddLineButton(), shapes.separate(height));
		Line toolbarEnd = new Line(0,height,y*2,height);
		
		//add action when button clicked
		shapes.QButton.setOnAction(e -> {
			//create shape, add listener to move it, add it to drawingArea
			Shape t = shapes.Qshape(50, 50);
			method.moveShape(t);
			drawingArea.getChildren().add(t);
		});
		shapes.MButton.setOnAction(e -> {
			Shape t = shapes.Mshape(50, 50);
			method.moveShape(t);
			drawingArea.getChildren().add(t);
		});
		shapes.DrageButton.setOnAction(e ->{
			method.mode = 'D';
		});
		shapes.LineButton.setOnAction(e ->{
			method.mode = 'L';
		});
		
		
		Group container = new Group();
		container.getChildren().addAll(toolbar,toolbarEnd,drawingArea);
		
		Scene scene = new Scene(container,x,y);
		window.setScene(scene);
		window.show();
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
