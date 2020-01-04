package Presenter;

import Model.OrdersModel;
import Model.ProfileModel;
import Services.SceneService;
import View.OrdersView;
import View.ProfileView;

public class OrdersPresenter extends Presenter {

    private OrdersView view;
    private OrdersModel model;

    public OrdersPresenter (OrdersView view) {
        this.view = view;
        model = new OrdersModel(this);
    }
}
