package models;

public class Product {

	private String Name,Color;
	
	
	public Product(String Name , String Color) {
		this.Name = Name;
		this.Color = Color;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	@Override
	public String toString() {
		return "Product [Name=" + Name + ", Color=" + Color + "]";
	}
	
}
