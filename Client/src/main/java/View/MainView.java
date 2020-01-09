package View;

import Presenter.MainPresenter;
import Services.SceneService;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView extends View implements Initializable {

    private MainPresenter presenter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new MainPresenter(this);
    }
}
