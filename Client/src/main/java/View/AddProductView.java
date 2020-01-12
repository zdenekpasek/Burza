package View;

import Presenter.AddProductPresenter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// view pro přidávání produktů, předává data presenteru
public class AddProductView extends View implements Initializable {

    private AddProductPresenter presenter;

    private final int MIN_PRICE = 0;
    private final int MAX_PRICE = 9999999;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField descriptionInput;

    @FXML
    private TextField priceInput;

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

    @FXML
    private Label addedProductLabel;

    @FXML
    private Label errorProductLabel;

    // cesta k souboru (obrázku produktu)
    private String chosenFilePath = "";

    // validuje jméno produktu podle regulárního výrazu
    // jméno může obsahovat malá, velká písmena, čísla, podtržítko, pomlčku
    // a musí mít minimálně 4 znaky a maximálně 16
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

    // validuje popis produktu, který má omezení na 100 znaků
    public boolean checkProductDescription(String description){
        if(description.length() > 100){
            errorDescription.setVisible(true);
            errorDescription.setText("Description is too long. (Max 100 characters)");
            return false;
        } else{
            return true;
        }
    }

    // validuje cenu produktu
    public boolean checkProductPrice(int price){
        if((price > MIN_PRICE) && (price < MAX_PRICE )){
            return true;
        } else{
            errorPrice.setVisible(true);
            errorPrice.setText("Invalid price. (Minimal price 1 / Maximal price 999999)");
            return false;
        }
    }

    // metoda, která po kliknutí tlačítka v addProductScreenu otevře okno pro výběr obrázku (jpg, png)
    // pokud obrázek není null, metoda vrátí cestu k souboru a vypíše jí na obrazovku
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

    // vezme user inputy o produktu a předá je presenteru
    @FXML
    void addProduct(ActionEvent evt){
        String name = nameInput.getText();
        String description = descriptionInput.getText();
        String priceText = priceInput.getText();
        int price = Integer.parseInt(priceText);
        presenter.validProduct(name, description, price, chosenFilePath, categoryInput.getText());
    }

    // metoda volána po úspěšném přidání produktu
    // nastaví a zobrazí label product added
    public void productAdded(){
        addedProductLabel.setText("Product added!");
        addedProductLabel.setVisible(true);
    }

    // metoda volána po neúspěšném přidání produktu
    // nastaví a zobrazí label error while adding product
    public void productNotAdded() {
        errorProductLabel.setText("Error while adding product!");
        errorProductLabel.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new AddProductPresenter(this);

        // listener pro priceInput - je možné zadávat jen čísla
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
