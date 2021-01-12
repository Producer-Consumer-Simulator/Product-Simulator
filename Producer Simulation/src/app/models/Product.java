package app.models;

import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

public class Product {

	private Color fxcolor ;
	private SimpleStringProperty name;
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

	public Product(String fName) {
		this.name = new SimpleStringProperty(fName);
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


}
