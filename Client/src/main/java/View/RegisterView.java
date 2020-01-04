package View;

import Presenter.RegisterPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterView implements Initializable {

    private RegisterPresenter presenter;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField adressInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label errorPassword;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorName;

    @FXML
    private Label errorAdress;

    public void goToSignInScreen(){
        presenter.openWindow("loginScreen.fxml");
    }

    public boolean checkEmail(String email){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher mtch = emailPattern.matcher(email);
        if(mtch.find()) {
            return true;
        } else{
            errorEmail.setVisible(true);
            errorEmail.setText("Email is not valid!");
            emailInput.clear();
            return false;
        }
    }

    public boolean checkName(String name){
        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9_-]{4,16}$");
        Matcher mtch = namePattern.matcher(name);
        if(mtch.find()){
            return true;
        } else{
            errorName.setVisible(true);
            errorName.setText("Incorrect name");
            nameInput.clear();
            return false;
        }
    }

    public boolean checkPassword(String password){
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,16}$");
        Matcher mtch = passwordPattern.matcher(password);
        if(mtch.find()){
            return true;
        } else{
            errorPassword.setVisible(true);
            errorPassword.setText("Your password needs to: " +
                    "\n - include both lower and upper case characters " +
                    "\n - include at least one number" +
                    "\n - be at least 6 characters long");

            passwordInput.clear();
            return false;
        }
    }

    public boolean checkAdress(String adress){
        if((adress.length() > 4) && (adress.length() < 25)){
            return true;
        } else{
            errorAdress.setVisible(true);
            errorAdress.setText("Wrong adress.");
            adressInput.clear();
            return false;
        }
    }


    @FXML
    void registerUser(ActionEvent evt){
        hideLabels();
        String email = emailInput.getText();
        String name = nameInput.getText();
        String adress = adressInput.getText();
        String password = passwordInput.getText();

        presenter.validUser(email, name, adress, password);

    }

    public void hideLabels(){
        errorName.setVisible(false);
        errorEmail.setVisible(false);
        errorPassword.setVisible(false);
        errorAdress.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new RegisterPresenter(this);

    }

}
