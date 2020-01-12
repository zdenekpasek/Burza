package Presenter;

import Model.OrderData;
import Model.OrdersModel;
import Model.ProfileModel;
import Services.SceneService;
import View.OrdersView;
import View.ProfileView;

import java.util.List;

public class OrdersPresenter extends Presenter {

    private OrdersView view;
    private OrdersModel model;

    public OrdersPresenter (OrdersView view) {
        this.view = view;
        model = new OrdersModel(this);
    }

    public void getOrders(boolean refresh) {
        model.getOrders(refresh);
    }

    public void setAllOrders(List<OrderData> orders) {
        view.setTableData(orders);
    }
}
