package View;

import Presenter.OrdersPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersView implements Initializable {

    private OrdersPresenter presenter;

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
        presenter = new OrdersPresenter(this);
    }
}
