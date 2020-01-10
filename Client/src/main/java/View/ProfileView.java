package View;

import Presenter.ProfilePresenter;
import Services.SceneService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private Label ordersLabelFromDb;



    private ProfilePresenter presenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        presenter = new ProfilePresenter(this);


    }
}
