package Presenter;

import Model.AddProductModel;
import View.AddProductView;

// třída příjmá data z addProductView a předává je addProductPresenteru
public class AddProductPresenter extends Presenter {

    private AddProductView view;
    private AddProductModel model;

    // metoda, která přijme informace od produktu od view, zkontroluje zda jsou veškeré inputy validní a předá inputy dál modelu
    // pokud bude validace špatná, dojde opět k validaci dat
    public void validProduct(String productName, String productDescription, int productPrice, String productPhotoPath, String category){
        if(view.checkProductName(productName) && view.checkProductDescription(productDescription) && view.checkProductPrice(productPrice)){
            model.addProduct(productName, productDescription, productPrice, productPhotoPath, category);
            System.out.println("Valid input, product added.");
        } else{
            System.out.println("Invalid input");
            view.checkProductDescription(productDescription);
            view.checkProductPrice(productPrice);
        }
    }

    public AddProductPresenter (AddProductView view) {
        this.view = view;
        model = new AddProductModel(this);
    }

    // metoda, která se zavolá po úspěšném přidání produktu
    // vypíše se úspěch a zavolá se metoda z view
    public void addProductSucessfull() {
        System.out.println("Product added!");
        view.productAdded();
    }

    // metoda, která se zavolá po neúspěšném přidání produktu
    // vypíše se neúspěch a zavolá se metoda z view
    public void addProductFailed() {
        System.out.println("Error while adding product!");
        view.productNotAdded();
    }
}
