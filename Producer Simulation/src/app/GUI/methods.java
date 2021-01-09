package app.GUI;

import java.util.ArrayList;

import app.GUI.model.DecoShape;
import app.GUI.model.InfoHolder;
import app.models.ProducerConsumer;
import app.models.Product;
import app.models.Unit;
import app.services.UnitBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.util.Callback;

public class methods {
	
	private InfoHolder info;
	public char mode = 'D';// 'D' for drag, 'L' for line
	class Delta { double x, y; }
	boolean FirstNodeClicked = false;
	
	public methods(InfoHolder info) {
		this.info = info;
	}
	
	public void HandleShape(DecoShape shape) {
		
		final Delta dragDelta = new Delta();
		
		shape.getNode().setOnMousePressed(e -> {
			if(mode == 'D' && shape.dragable) {
			    // record a delta distance for the drag and drop operation.
			    dragDelta.x = shape.getNode().getLayoutX() - e.getSceneX();
			    dragDelta.y = shape.getNode().getLayoutY() - e.getSceneY();
			}
			else if(mode == 'L') {
				if(!FirstNodeClicked) {
					info.twosides[0] = shape;
					FirstNodeClicked = true;
				}
				else {
					info.twosides[1] = shape;
					FirstNodeClicked = false;
					if(connectUs()) { 
						System.out.println("connected!");
						info.twosides[1].setPrevious(info.twosides[0]);
						drawLine();						
					}
					else
						System.out.println("not connected!");
				}
			}
		});
		
		shape.getNode().setOnMouseReleased(e -> shape.getNode().setCursor(Cursor.HAND));
		
		shape.getNode().setOnMouseDragged(e -> {
			if(mode == 'D' && shape.dragable) {
				shape.getNode().setLayoutX(e.getSceneX() + dragDelta.x);
				shape.getNode().setLayoutY(e.getSceneY() + dragDelta.y);
			}
		});
		
		shape.getNode().setOnMouseEntered(e -> {
			shape.getNode().setCursor(Cursor.HAND);
			shape.getShape().setStrokeWidth(3);
		});
		
		shape.getNode().setOnMouseExited(e -> {
			shape.getNode().setCursor(Cursor.HAND);
			shape.getShape().setStrokeWidth(0);
		});
	}
	
	private boolean connectUs() {
		char id1 = info.twosides[0].getType();
		char id2 = info.twosides[1].getType();
		if(id1==id2) {
			System.out.println(id1+" to "+id1);
			return false;
		}
		else if(info.root == null) {
			System.out.println("not root");
			info.root = info.twosides[0];
			info.twosides[0].dragable = !info.twosides[0].setNext(info.twosides[1]);
			info.twosides[1].dragable = info.twosides[0].dragable;
			return !info.twosides[0].dragable;
		}
		
		ArrayList<DecoShape> pre = info.twosides[0].getPrevious();
		for(int i=0; i<pre.size(); i++) {
			if(info.twosides[1] == pre.get(i)) {
				System.out.println("already connected!");
				return false;
			}
		}

		DecoShape ds = new DecoShape(); 
		if(ds.search(info.root,info.twosides[0])) {
			System.out.println("\nshape found!");
			info.twosides[0].dragable = !info.twosides[0].setNext(info.twosides[1]);
			info.twosides[1].dragable = info.twosides[0].dragable;
			return !info.twosides[0].dragable;
		}
		System.out.println("\nno path from root found");
		return false;
	}
	private void drawLine(){
		double[] atr = new double[] {
				info.twosides[0].getNode().getLayoutX() + info.twosides[0].getNode().getWidth()/2,
				info.twosides[0].getNode().getLayoutY() + info.twosides[0].getNode().getHeight()/2,
				info.twosides[1].getNode().getLayoutX() + info.twosides[1].getNode().getWidth()/2,
				info.twosides[1].getNode().getLayoutY() + info.twosides[1].getNode().getHeight()/2
		};
		//line
		Line t = new Line(atr[0],atr[1],atr[2],atr[3]); 
		info.drawingArea.getChildren().add(t);
		t.toBack();
		//some calculations
		double xDiff = atr[2]-atr[0], yDiff = atr[3]-atr[1];
		double length = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
		double angle = Math.atan(yDiff/xDiff);
		if(xDiff == 0) {
			angle = (yDiff>0)?Math.PI/2:Math.PI*3/2;
		}
		if(xDiff < 0)
			angle += Math.PI;
		double newX = atr[0]+((length/2)*Math.cos(angle))+5*Math.sin(angle);
		double newY = atr[1]+((length/2)*Math.sin(angle))-5*Math.cos(angle);
		//arrow
		Polygon polygon = new Polygon();
		polygon.getPoints().addAll(new Double[]{0.0,0.0,20.0,5.0,0.0,10.0});
		polygon.setLayoutX(newX); polygon.setLayoutY(newY);
		polygon.getTransforms().add(new Rotate((angle*180/Math.PI),0,0)); 
		info.drawingArea.getChildren().add(polygon);
		polygon.toBack();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public void fillTable(ArrayList<Product> products) {
		
		final ObservableList<Product> data = fillData(products);
		
		info.table.setEditable(true);
		 
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setStyle("-fx-alignment: CENTER");
        firstNameCol.prefWidthProperty().bind(info.table.widthProperty().divide(1));
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("firstName"));
        info.table.setItems(data);
        
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
        info.table.getColumns().addAll(firstNameCol);
        info.table.relocate(1000, 400);
		info.table.setId("my-table");
        info.table.setPrefWidth(300);
        info.table.setPrefHeight(250);
	}
	
	private ObservableList<Product> fillData(ArrayList<Product> products){
		final ObservableList<Product> data = FXCollections.observableArrayList();
		for(int i=0; i<products.size(); i++) {
			data.add(products.get(i));
		}
		return data;
	}
	
	public static void Simulate() {
		InfoHolder info = InfoHolder.getInstance();
		UnitBuilder ub = new UnitBuilder();
		treetoUnitBuilder(info.root, ub);
		Unit u = ub.toUnit();
		Product p1 = new Product("p1"), p2 = new Product("p2"),
		p3 = new Product("p3"), p4 = new Product("p4"),
		p5 = new Product("p5"), p6 = new Product("p6");
		p1.fxcolor = Color.RED; p2.fxcolor = Color.GREEN;
		p3.fxcolor = Color.YELLOW; p4.fxcolor = Color.BLUE;
		p5.fxcolor = Color.PINK; p6.fxcolor = Color.CYAN;
		u.addProduct(p1); u.addProduct(p2); u.addProduct(p3);
		u.addProduct(p4); u.addProduct(p5); u.addProduct(p6);
		System.out.println(u);
		/*Thread t = new Thread (new ProducerConsumer());
		t.start(); */
		ProducerConsumer pc = new ProducerConsumer();
		
		Thread t1 = new Thread(new Runnable() { 
            @Override
            public void run() 
            { 
            	pc.Consume(); 
            } 
        });
		t1.start();
		//new Thread (new Producer()).start();;
	}
	
	private static void treetoUnitBuilder(DecoShape root, UnitBuilder ub) {
		if(root == null) return;
		else if(root.getNext().size() == 0) return;
		else {
			for(int i=0; i<root.getNext().size();i++) {
				if(root.getType() == 'M'/* && !root.visited*/) {
					root.visited = true;
					ub.CreateMachine(root,root.getTextString(),root.getPrevious().get(0).getTextString(), root.getNext().get(0).getTextString());
				}
				treetoUnitBuilder(root.getNext().get(i), ub);
			}
		}
	}
}
