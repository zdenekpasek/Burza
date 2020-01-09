package View;

import Presenter.RegisterPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

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
    private TextField cityInput;

    @FXML
    private TextField zipInput;

    @FXML
    private TextField countryInput;

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

    // validuje email user input podle regulárního výrazu, pokud vyhovuje regexu vrací true, v opačném případě false
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
    // validuje name user input podle regulárního výrazu, pokud vyhovuje regexu vrací true, v opačném případě false
    // povoluje malá, velká písmena, čísla a podtržítko - jméno musí mít minimálně 4 znaky a maximálne 16 znaků
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
    // validuje password user input podle regulárního výrazu, pokud vyhovuje regexu vrací true, v opačném případě false
    // heslo musí obashovat jak malá písmena, tak velká, dále musí mít minimálně jedno číslo a minimální délka hesla je 6 znaků
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

    // validuje address user input podle jednoduchých podmínek (délka vstupů), pokud vyhovuje podmínkám vrací true, v opačném případě false
    public boolean checkAdress(String city, String country, String ZIP){
        if((city.length() > 3) && (city.length() < 25) && (country.length() > 3) && (country.length() < 30) && (ZIP.length() >= 5) && (ZIP.length() < 10) ){
            return true;
        } else{
            errorAdress.setVisible(true);
            errorAdress.setText("Wrong adress.");
            cityInput.clear();
            countryInput.clear();
            zipInput.clear();
            return false;
        }
    }

    // metoda pro hashování hesla - využita knihovna BCrypt
    public String hashPassword(String pass){
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    // sesbírá veškeré potřebné inputy pro registraci usera a předá je registerPresenteru
    @FXML
    void registerUser(ActionEvent evt){
        hideLabels();
        String email = emailInput.getText();
        String name = nameInput.getText();
        String city = cityInput.getText();
        String country = countryInput.getText();
        String zip = zipInput.getText();
        String password = passwordInput.getText();

        presenter.validUser(email, name, city, country, zip, password);

    }

    // schová veškeré input error labely
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
