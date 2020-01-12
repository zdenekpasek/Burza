package View;

import Presenter.ProfilePresenter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ResourceBundle;

// view pro obrazovku profilu, předává data presenteru
public class ProfileView extends View implements Initializable  {

    @FXML
    private Circle testCircle;

    @FXML
    private Label nameLabelFromDb;

    @FXML
    private Label emailLabelFromDb;

    @FXML
    private Label countryLabelFromDb;

    @FXML
    private Label cityLabelFromDb;

    @FXML
    private Label ordersLabelFromDb;

    @FXML
    private Label adressZIPfromDb;


    private ProfilePresenter presenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new ProfilePresenter(this);
    }

    // bere data z presenteru a nastavuje informace o uživateli
    public void setUserProfile(String userName, String email, String country, String city, String adressZIP){
        cityLabelFromDb.setText(city);
        nameLabelFromDb.setText(userName);
        emailLabelFromDb.setText(email);
        countryLabelFromDb.setText(country);
        adressZIPfromDb.setText(adressZIP);
    }
}
