package View;

import Presenter.RemoveProductPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveProductView extends View implements Initializable {

    @FXML
    private TextField nameInput;

    @FXML
    private Label errorName;

    @FXML
    private Label productLabel;

    private RemoveProductPresenter presenter;


    public boolean checkProductName(String name){
        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9_-]{4,16}$");
        Matcher mtch = namePattern.matcher(name);
        if(mtch.find()){
            return true;
        } else{
            errorName.setVisible(true);
            errorName.setText("Wrong format");
            nameInput.clear();
            return false;
        }
    }

    @FXML
    void removeProduct(ActionEvent evt) {
        String productName = nameInput.getText();
        presenter.validProduct(productName);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        presenter = new RemoveProductPresenter(this);


    }
}