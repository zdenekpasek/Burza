package Presenter;

import Model.AddProductModel;
import Model.BasketModel;
import Services.SceneService;
import View.AddProductView;
import View.BasketView;

public class AddProductPresenter extends Presenter {

    private AddProductView view;
    private AddProductModel model;

    public void validProduct(String name, String description, int price){
        if(view.checkProductName(name) && view.checkProductDescription(description) && view.checkProductPrice(price)){
            System.out.println("Valid input, product added.");
        } else{
            System.out.println("Invalid input");
            view.checkProductDescription(description);
            view.checkProductPrice(price);
        }
    }

    public AddProductPresenter (AddProductView view) {
        this.view = view;
        model = new AddProductModel(this);
    }
}
