package View;

import Presenter.MainView.MainPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    private MainPresenter presenter;

    public void buttonOnAction(){
       presenter.openWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new MainPresenter(this);
    }
}
