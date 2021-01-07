package GUI.model;

import javafx.beans.property.SimpleStringProperty;

public class Product {
	
    private final SimpleStringProperty name;

    
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
