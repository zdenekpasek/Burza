package Presenter;

import Model.BasketModel;
import Model.ProfileModel;
import Services.SceneService;
import View.BasketView;
import View.ProfileView;

public class BasketPresenter {

    private BasketView view;
    private BasketModel model;

    public void openWindow(String scene){
        try {
            SceneService.switchScene(scene);
        }catch (Exception e){
            System.out.println("e = " + e);

        }
    }

    public BasketPresenter (BasketView view) {
        this.view = view;
        model = new BasketModel(this);
    }
}
