package View;

import Presenter.RegisterPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterView implements Initializable {

    private RegisterPresenter presenter;

    public void goToSignInScreen(){
        presenter.openWindow("loginScreen.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new RegisterPresenter(this);
    }
}
