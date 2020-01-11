package Model;

import javafx.beans.property.SimpleStringProperty;

public class ProductData {

    private SimpleStringProperty productID;
    private SimpleStringProperty productName;
    private SimpleStringProperty category;
    private SimpleStringProperty productDescription;
    private SimpleStringProperty productPrice;

    public ProductData(String productID, String productName, String category, String productDescription, String productPrice){
        this.productID = new SimpleStringProperty(productID);
        this.productName = new SimpleStringProperty(productName);
        this.category = new SimpleStringProperty(category);
        this.productDescription = new SimpleStringProperty(productDescription);
        this.productPrice = new SimpleStringProperty(productPrice);
    }

    public String getProductID() {
        return productID.get();
    }

    public String getProductName() {
        return productName.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getProductDescription() {
        return productDescription.get();
    }

    public String getProductPrice() {
        return productPrice.get();
    }

}
