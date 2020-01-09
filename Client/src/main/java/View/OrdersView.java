package View;

import Presenter.OrdersPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersView extends View implements Initializable {

    private OrdersPresenter presenter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new OrdersPresenter(this);
    }
}
