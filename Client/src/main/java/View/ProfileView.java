package View;

import Presenter.ProfilePresenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileView implements Initializable {

    @FXML
    private Circle testCircle;

    private ProfilePresenter presenter;

    public void goToProfileScreen(){
        presenter.openWindow("profileScreen.fxml");
    }
    public void goToMainScreen(){
        presenter.openWindow("mainScreen.fxml");
    }
    public void goToOrdersScreen(){
        presenter.openWindow("ordersScreen.fxml");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        presenter = new ProfilePresenter(this);


    }
}
