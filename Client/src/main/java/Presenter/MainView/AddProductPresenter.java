package Presenter;

import Model.AddProductModel;
import Model.BasketModel;
import Services.SceneService;
import View.AddProductView;
import View.BasketView;

public class AddProductPresenter {

    private AddProductView view;
    private AddProductModel model;

    public void openWindow(String scene){
        try {
            SceneService.switchScene(scene);
        }catch (Exception e){
            System.out.println("e = " + e);

        }
    }

    public AddProductPresenter (AddProductView view) {
        this.view = view;
        model = new AddProductModel(this);
    }
}
