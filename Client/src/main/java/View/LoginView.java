package View;


import Presenter.LoginPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

// view pro přihlášení uživatele, předává data presenteru
public class LoginView implements Initializable {

    private LoginPresenter presenter;

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label errorLabel;

    // metoda pro přepnutí scény na registerScreen
    public void goToRegisterScreen(){
        presenter.openWindow("registerScreen.fxml");
    }

    // po stisknutí tlačítka sign in se z user inputů vezmou data a předají se dál presenteru
    @FXML
    void signIn(ActionEvent evt){
        String email = emailInput.getText();
        String password = passwordInput.getText();
        presenter.getLoginData(email, password);
    }

    // metoda, která se zavolá po neúspěšném přihlášení uživatele
    public void wrongCredentials(){
        errorLabel.setText("Login failed, wrong credentials!");
        errorLabel.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new LoginPresenter(this);
    }
}
