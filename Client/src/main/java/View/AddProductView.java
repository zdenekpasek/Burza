package View;

import Presenter.AddProductPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductView implements Initializable {

    private AddProductPresenter presenter;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField descriptionInput;

    @FXML
    private TextField priceInput;

    @FXML
    private ComboBox categoryComboBox;



    public void goToProfileScreen(){
        presenter.openWindow("profileScreen.fxml");
    }
    public void goToMainScreen(){
        presenter.openWindow("mainScreen.fxml");
    }
    public void goToOrdersScreen(){
        presenter.openWindow("ordersScreen.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new AddProductPresenter(this);
    }
}
