package Presenter;

import Model.OrdersModel;
import Model.ProfileModel;
import Services.SceneService;
import View.OrdersView;
import View.ProfileView;

public class OrdersPresenter {

    private OrdersView view;
    private OrdersModel model;

    public void openWindow(String scene){
        try {
            SceneService.switchScene(scene);
        }catch (Exception e){
            System.out.println("e = " + e);

        }
    }

    public OrdersPresenter (OrdersView view) {
        this.view = view;
        model = new OrdersModel(this);
    }
}
