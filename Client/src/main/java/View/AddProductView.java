package View;

import Presenter.AddProductPresenter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddProductView extends View implements Initializable {

    private AddProductPresenter presenter;

    private final int MIN_PRICE = 0;
    private final int MAX_PRICE = 999999;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField descriptionInput;

    @FXML
    private TextField priceInput;

    @FXML
    private ComboBox categoryComboBox;

    @FXML
    private TextField categoryInput;

    @FXML
    private Label errorName;

    @FXML
    private Label errorDescription;

    @FXML
    private Label errorPrice;

    @FXML
    private Label pathLabel;

    private String chosenFilePath = "";




    public boolean checkProductName(String name){
        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9_-]{4,16}$");
        Matcher mtch = namePattern.matcher(name);
        if(mtch.find()){
            return true;
        } else{
            errorName.setVisible(true);
            errorName.setText("Wrong name");
            nameInput.clear();
            return false;
        }
    }

    public boolean checkProductDescription(String description){
        if(description.length() > 100){
            errorDescription.setVisible(true);
            errorDescription.setText("Description is too long. (Max 100 characters)");
            return false;
        } else{
            return true;
        }
    }

    public boolean checkProductPrice(int price){
        if((price > MIN_PRICE) && (price < MAX_PRICE )){
            return true;
        } else{
            errorPrice.setVisible(true);
            errorPrice.setText("Invalid price. (Minimal price 1 / Maximal price 999999)");
            return false;
        }
    }


    public String fileChoose(){
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fc.showOpenDialog(null);
        if(file != null){
            pathLabel.setText(file.getAbsolutePath());
            chosenFilePath = file.getAbsolutePath();
            return chosenFilePath;
        }
        return "error";

    }

    @FXML
    void addProduct(ActionEvent evt){
        String name = nameInput.getText();
        String description = descriptionInput.getText();
        String priceText = priceInput.getText();
        int price = Integer.parseInt(priceText);
        presenter.validProduct(name, description, price, chosenFilePath);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new AddProductPresenter(this);

        categoryComboBox.getItems().addAll(
                "Electronics",
                "Books",
                "Cars",
                "Sport",
                "Music",
                "Furniture",
                "Clothing"
        );

        priceInput.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    priceInput.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }
}
