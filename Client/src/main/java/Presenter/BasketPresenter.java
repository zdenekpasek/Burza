package Presenter;

import Model.BasketModel;
import Services.SceneService;
import View.BasketView;

public class BasketPresenter extends Presenter{

    private BasketView view;
    private BasketModel model;

    public BasketPresenter (BasketView view) {
        this.view = view;
        model = new BasketModel(this);
    }
}
