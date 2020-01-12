package View;

import Model.ProductData;
import Presenter.MainPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// view pro hlavní obrazovku, předává data presenteru
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

    // metoda volaná po stisknutí tlačítka refresh, volá metodu z presenteru
    public void refreshProducts(){
        presenter.getAllProducts(true);
    }

    // metoda volaná po stisknutí tlačítka buy, vezme údaje z table view a předá presenteru
    public void buyProduct(){
        if(presenter.buyProduct(table.getSelectionModel().getSelectedItem())){
            table.getItems().remove(table.getSelectionModel().getSelectedItem());
            table.refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new MainPresenter(this);

        name.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productName"));
        category.setCellValueFactory(new PropertyValueFactory<ProductData, String>("category"));
        description.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productDescription"));
        price.setCellValueFactory(new PropertyValueFactory<ProductData, String>("productPrice"));

        presenter.getAllProducts(false);
    }

    // plní sloupečky datama v tableView produktů
    public void setTableData(List<ProductData> products) {
        ObservableList<ProductData> list = FXCollections.observableArrayList();
        list.addAll(products);
        table.getItems().clear();
        table.setItems(list);
    }

    // metoda volaná po stisknutí tlačíka show detail, bere data z tableView a předává presenteru
    public void showPicture(ActionEvent actionEvent) {
        presenter.showPicture(table.getSelectionModel().getSelectedItem().getProductID());
    }

    // nastavuje vyskakovací okno a vkládá obrázek produktu
    public void showPictureWindow(byte[] picture) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        Image image = new Image(new ByteArrayInputStream(picture));
        ImageView imageView = new ImageView(image);
        dialogVbox.getChildren().add(imageView);
        Scene dialogScene = new Scene(dialogVbox, image.getWidth(), image.getHeight());
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
