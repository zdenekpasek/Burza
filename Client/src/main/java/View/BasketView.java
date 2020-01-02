package View;

import Presenter.BasketPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BasketView implements Initializable {

    private BasketPresenter presenter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new BasketPresenter(this);
    }
}
