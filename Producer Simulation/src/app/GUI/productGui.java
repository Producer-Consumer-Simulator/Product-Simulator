package app.GUI;
	
import java.util.ArrayList;

import app.models.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class productGui extends Application implements Runnable {
	
	private String[] args;
	
	public TextField textField;
	public TableView<Product> table;
	public ArrayList<Product> productInput = new ArrayList<Product>();
	public Button addproduct;
	String textNull = "\n";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			table = new TableView<Product>();
			HBox hbox = new HBox();
			hbox.setPadding(new Insets(15, 12, 15, 12));
			hbox.setPrefWidth(400);
			hbox.getStyleClass().add("hbox");
			hbox.setSpacing(10);
			hbox.setStyle("-fx-background-color: #FFF;");
			
			addproduct = new Button("add product");
			addproduct.setPrefSize(150, 40);
			addproduct.getStyleClass().add("btn-save");
			addproduct.setOnMouseClicked(e -> {
				String text = textField.getText();
				if (!text.equals(textNull)) {
					productInput.add(new Product(text));
					fillTable(productInput);
				}
			});

			textField = new TextField();
			textField.getStyleClass().add("text-field");
			textField.setPrefSize(250, 40);
			textField.setPromptText("product name");
			
			table.relocate(50, 100);
			table.setId("my-table");
			table.setPrefWidth(300);
			table.setPrefHeight(500);
			
			
			
			hbox.getChildren().addAll( textField,addproduct);
			root.getChildren().addAll(hbox,table);
			
			fillTable(productInput);
			
			Scene scene = new Scene(root,400,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public void fillTable(ArrayList<Product> products) {
		
		final ObservableList<Product> data = fillData(products);
		
		table.setEditable(true);
		 
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setStyle("-fx-alignment: CENTER");
        firstNameCol.prefWidthProperty().bind(table.widthProperty().divide(1));
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("firstName"));
        table.setItems(data);
        
        firstNameCol.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn param) {
                return new TableCell<Product, String>() {
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                            setText(item);
                            TableRow<Product> row = getTableRow();
                        	if(row.getIndex()>=0&&row.getIndex()<data.size())
                        		row.setStyle("-fx-background-color:"+data.get(row.getIndex()).getColor()+";");
                    }
                };
            }
        });
        table.getColumns().addAll(firstNameCol);
	}
	
	private ObservableList<Product> fillData(ArrayList<Product> products){
		final ObservableList<Product> data = FXCollections.observableArrayList();
		for(int i=0; i<products.size(); i++) {
			data.add(products.get(i));
		}
		return data;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		launch(args);
	
	}
	
}
