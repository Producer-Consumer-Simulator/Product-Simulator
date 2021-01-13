package app.GUI.model;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class myshapes {

	public double x = 50, y = 50;
	private double Qwidth = 60, Qheight = 40;
	private double Mradius = 40;

	public Button QButton;
	public Button MButton;
	public Button DrageButton;
	public Button LineButton;
	public Button addproduct;
	public Button run;
	public Button reset;
	public TextField textField;
	public ListView<StackPane> tempList;
	
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
		HBox sep = new HBox(new Separator(), new Line(0, 0, 0, height), new Separator());
		return sep;
	}

	public void SetStackPane(StackPane sp) {
		sp.setLayoutX(x);
		sp.setLayoutY(y);
	}

	public Rectangle Qshape() {
		Rectangle Q = new Rectangle(0, 0, Qwidth, Qheight);
		Q.setFill(Color.GOLD);
		Q.setStroke(Color.GRAY);
		Q.setId("Q");
		return Q;
	}

	public Circle Mshape() {
		Circle M = new Circle(0, 0, Mradius);
		M.setFill(Color.GRAY);
		M.setStroke(Color.GOLD);
		M.setId("M");
		return M;
	}

	public HBox toolbar() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setPrefWidth(1300);
		hbox.getStyleClass().add("hbox");
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #FFF;");

		Button buttonqueue = AddQbutton();
		buttonqueue.setPrefSize(120, 40);
		buttonqueue.getStyleClass().add("btn-save");

		Button buttonmachine = AddMbutton();
		buttonmachine.setPrefSize(120, 40);
		buttonmachine.getStyleClass().add("btn-save");

		Button buttonmove = AddDrageButton();
		buttonmove.setPrefSize(120, 40);
		buttonmove.getStyleClass().add("btn-save");

		Button buttonconect = AddLineButton();
		buttonconect.setPrefSize(120, 40);
		buttonconect.getStyleClass().add("btn-save");

		run = new Button("Run");
		run.setPrefSize(120, 40);
		run.getStyleClass().add("btn-save");
		
		reset = new Button("Reset");
		reset.setPrefSize(120, 40);
		reset.getStyleClass().add("btn-save");
		
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		addproduct = new Button("add product");
		addproduct.setPrefSize(120, 40);
		addproduct.getStyleClass().add("btn-save");

		textField = new TextField();
		textField.getStyleClass().add("text-field");
		textField.setPrefSize(250, 40);
		textField.setPromptText("product name");
		hbox.getChildren().addAll(buttonqueue, buttonmachine, buttonmove, buttonconect, run, reset, spacer, textField,
				addproduct);

		return hbox;
	}
	public StackPane productRow(String name, Color tColor, Color bcolor) {
		//text
		Text text = new Text(name);
		text.setFill(tColor);
		text.setStyle("-fx-font: 20 arial;");
		//background and border
		Rectangle rect = new Rectangle();
		rect.setWidth(115); rect.setHeight(30);
		rect.setFill(bcolor);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(2);
	
		return new StackPane(rect,text);
	}
	public VBox productList(String name) {
		Label title = new Label(name);
		ListView<StackPane> list = new ListView<StackPane>();
		list.setMaxHeight(280); list.setMaxWidth(150);
		tempList = list;
		return new VBox(title,list);
	}
	public HBox productInfo(VBox v1, VBox v2, VBox v3) {
		HBox pro = new HBox(v1,v2,v3);
		pro.getStyleClass().add("hbox");
		pro.setStyle("-fx-padding: 5px");
		return pro;
	}

}
