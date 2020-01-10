package Presenter;


import Model.RemoveProductModel;
import View.RemoveProductView;

public class RemoveProductPresenter extends Presenter{

    private RemoveProductView view;
    private RemoveProductModel model;

    public RemoveProductPresenter (RemoveProductView view) {
        this.view = view;
        model = new RemoveProductModel(this);
    }

    public void validProduct(String productName) {
        if(view.checkProductName(productName)){
            model.removeProduct(productName);
            System.out.println("Valid format of product to remove.");
        } else{
            System.out.println("Invalid input");
        }
    }

    public void removeProductSuccessfull() {
        System.out.println("Sucessfully removed product.");
    }

    public void removeProductFailed() {
        System.out.println("Failed removing product.");
    }
}
