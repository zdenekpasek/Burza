package View;


import Presenter.LoginPresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements Initializable {

    private LoginPresenter presenter;

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    public void goToRegisterScreen(){
        presenter.openWindow("registerScreen.fxml");
    }

    public void signInButton(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new LoginPresenter(this);
    }
}
