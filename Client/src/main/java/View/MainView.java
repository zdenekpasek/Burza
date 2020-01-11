package View;

import Model.ProductData;
import Presenter.MainPresenter;
import Services.SceneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainView extends View implements Initializable {

    private MainPresenter presenter;

    @FXML
    private TableView<ProductData> table;
    @FXML
    public TableColumn<ProductData, String> name;

    @FXML
    public TableColumn<ProductData, String> category;

    @FXML
    public TableColumn<ProductData, String> description;

    @FXML
    public TableColumn<ProductData, String> price;

    public void refreshProducts(){
        presenter.getAllProducts();
    }

    public void buyProduct(){

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new MainPresenter(this);

        name.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productName"));
        category.setCellValueFactory(new PropertyValueFactory<ProductData, String>("category"));
        description.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productDescription"));
        price.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productPrice"));
    }

    public void setTableData(List<ProductData> products) {
        ObservableList<ProductData> list = FXCollections.observableArrayList();
        list.addAll(products);
//        for(ProductData data : list){
//            System.out.println(data.getProductID() + " " + data.getProductName() + " " + data.getCategory() + " " + data.getProductDescription() + " " + data.getProductPrice());
//        }
        table.getItems().clear();
        table.setItems(list);
    }

    public void showPicture(ActionEvent actionEvent) {
    }
}
