package Presenter;

import Model.AddProductModel;
import Model.BasketModel;
import Services.SceneService;
import View.AddProductView;
import View.BasketView;

public class AddProductPresenter extends Presenter {

    private AddProductView view;
    private AddProductModel model;

    public void validProduct(String productName, String productDescription, int productPrice, String productPhotoPath){
        if(view.checkProductName(productName) && view.checkProductDescription(productDescription) && view.checkProductPrice(productPrice)){
            model.addProduct(productName, productDescription, productPrice, productPhotoPath);
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

    public void addProductSucessfull() {
    }

    public void addProductFailed() {
    }
}
