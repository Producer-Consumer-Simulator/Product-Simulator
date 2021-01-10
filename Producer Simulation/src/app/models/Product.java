package app.models;

//import java.awt.Color;
import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

public class Product {

	// private String Name,Color;
	private Color fxcolor ;
	/*private static String[] color1 = { "#F0F8FF", "FAEBD7", "#00FFFF", "#DC143C", "#FF7F50", "#7FFF00", "#0000FF ",
			"#8B008B", "#FF8C00", "#FF1493", "#00BFFF", "#B22222", "#FF00FF", "#FFD700", "#FF69B4", "#CD5C5C",
			"#4B0082", "#800000" };*/
	private SimpleStringProperty name;
	//private String color;

	public String getColor() {
		StringBuilder color = new StringBuilder();
		color.append('#');
		String s1 = Integer.toHexString((int) Math.round(fxcolor.getRed()*255));
		color.append(handleColorString(s1));
		s1 = Integer.toHexString((int) Math.round(fxcolor.getGreen()*255));
		color.append(handleColorString(s1));
		s1 = Integer.toHexString((int) Math.round(fxcolor.getBlue()*255));
		color.append(handleColorString(s1));
		return color.toString();
	}

	private String handleColorString(String s) {
		if (s.length()==1) {
			s="0"+s;
		}
		return s.toUpperCase();
	}
	/*public void setColor(String color) {
		this.color = color;
	}*/

	public Product(String fName) {
		this.name = new SimpleStringProperty(fName);
		//int n = (int) (Math.random() * (17 - 0 + 1) + 0);
		//this.color = color1[n];
		Random r = new Random();
		int red =  r.nextInt(255);
		int green =  r.nextInt(255);
		int blue =  r.nextInt(255);
		this.setFxcolor(Color.rgb(red, green, blue));
	}

	public String getFirstName() {
		return name.get();
	}

	public void setFirstName(String fName) {
		name.set(fName);
	}

	/*@Override
	public String toString() {
		return "Product [name=" + name + ", color=" + fxcolor.toString() + "]";
	}*/

	public Color getFxcolor() {
		return fxcolor;
	}

	@Override
	public String toString() {
		return "Product [fxcolor=" + fxcolor + ", name=" + name + "]";
	}

	public void setFxcolor(Color fxcolor) {
		this.fxcolor = fxcolor;
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
