package View;

import Presenter.MainView.MainPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    private MainPresenter presenter;

    public void goToProfileScreen(){
        presenter.openWindow("profileScreen.fxml");
    }
    public void goToMainScreen(){
        presenter.openWindow("mainScreen.fxml");
    }
    public void goToOrdersScreen(){
        presenter.openWindow("ordersScreen.fxml");
    }

    public void goToAddProductScreen(){
        presenter.openWindow("addProductScreen.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new MainPresenter(this);
    }
}
