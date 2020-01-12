package Presenter;

import Model.RemoveProductModel;
import View.RemoveProductView;

// třída příjmá data z removeProductView a předává je removeProductModelu
public class RemoveProductPresenter extends Presenter{

    private RemoveProductView view;
    private RemoveProductModel model;

    public RemoveProductPresenter (RemoveProductView view) {
        this.view = view;
        model = new RemoveProductModel(this);
    }

    // validuje název produktu a předává user input produktu modelu
    public void validProduct(String productName) {
        if(view.checkProductName(productName)){
            model.removeProduct(productName);
            System.out.println("Valid format of product to remove.");
        } else{
            System.out.println("Invalid input");
        }
    }

    // metoda vykonána po úspěšném odstranění produktu
    // volá metoda z view
    public void removeProductSuccessfull() {
        System.out.println("Sucessfully removed product.");
        view.removeSuccessfull();
    }

    // metoda vykonána po neúspěšném odstranění produktu
    // volá metoda z view
    public void removeProductFailed() {
        System.out.println("Failed removing product.");
        view.removeFail();
    }
}
