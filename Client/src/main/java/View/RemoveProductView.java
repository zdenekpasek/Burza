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

// view pro odstranění produktu, předává data presenteru
public class RemoveProductView extends View implements Initializable {

    @FXML
    private TextField nameInput;

    @FXML
    private Label errorName;

    @FXML
    private Label productLabel;

    @FXML
    private Label removeSuccLabel;

    @FXML
    private Label removeFailLabel;

    private RemoveProductPresenter presenter;

    // validuje název produktu podle regexu, ošetření vstupu
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

    // metoda volaná po stisknutí tlačítka remove product
    // načte data z user inputu a předá je presenteru
    @FXML
    void removeProduct(ActionEvent evt) {
        String productName = nameInput.getText();
        presenter.validProduct(productName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new RemoveProductPresenter(this);
    }

    // metoda volaná po úspěšném odstranění produktu
    public void removeSuccessfull() {
        removeSuccLabel.setText("Successfully removed product!");
        removeSuccLabel.setVisible(true);
    }

    // metoda volaná po neúspěšném odstranění produktu
    public void removeFail() {
        removeFailLabel.setText("Failed while removing product!");
        removeFailLabel.setVisible(true);
    }
}