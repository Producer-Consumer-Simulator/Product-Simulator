package producerConsumerApp.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

public class Product {

	// private String Name,Color;
	public Color fxcolor;
	private static String[] color1 = { "#F0F8FF", "FAEBD7", "#00FFFF", "#DC143C", "#FF7F50", "#7FFF00", "#0000FF ",
			"#8B008B", "#FF8C00", "#FF1493", "#00BFFF", "#B22222", "#FF00FF", "#FFD700", "#FF69B4", "#CD5C5C",
			"#4B0082", "#800000" };
	private SimpleStringProperty name;
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Product(String fName) {
		this.name = new SimpleStringProperty(fName);
		int n = (int) (Math.random() * (17 - 0 + 1) + 0);
		this.color = color1[n];

	}

	public String getFirstName() {
		return name.get();
	}

	public void setFirstName(String fName) {
		name.set(fName);
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", color=" + color + "]";
	}

	/*
	 * public Product(String Name , String Color) { this.Name = Name; this.Color =
	 * Color; }
	 * 
	 * public String getName() { return Name; }
	 * 
	 * public void setName(String name) { Name = name; }
	 * 
	 * public String getColor() { return Color; }
	 * 
	 * public void setColor(String color) { Color = color; }
	 * 
	 * @Override public String toString() { return "Product [Name=" + Name +
	 * ", Color=" + Color + "]"; }
	 */

}
