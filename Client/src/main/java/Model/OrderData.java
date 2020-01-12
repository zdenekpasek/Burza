package Model;

import javafx.beans.property.SimpleStringProperty;

public class OrderData {

    private SimpleStringProperty orderNumber;
    private SimpleStringProperty orderDate;
    private SimpleStringProperty productName;

    public OrderData(String orderNumber, String orderDate, String productName) {
        this.orderNumber =  new SimpleStringProperty(orderNumber);
        this.orderDate =  new SimpleStringProperty(orderDate);
        this.productName =  new SimpleStringProperty(productName);
    }

    public String getOrderNumber() {
        return orderNumber.get();
    }

    public String getOrderDate() {
        return orderDate.get();
    }


    public String getProductName() {
        return productName.get();
    }


}
