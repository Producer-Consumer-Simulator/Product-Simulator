package GUI.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;

public class Product {
	
    private SimpleStringProperty name;
    private Color color;
    
    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	private Product(String fName) {
        this.name = new SimpleStringProperty(fName);

    }

    public String getFirstName() {
        return name.get();
    }

    public void setFirstName(String fName) {
    	name.set(fName);
    }
}
