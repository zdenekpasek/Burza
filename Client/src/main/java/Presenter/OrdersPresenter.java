package Presenter;

import Model.OrderData;
import Model.OrdersModel;
import View.OrdersView;

import java.util.List;

// třída příjmá data z ordersView a předává je ordersModelu
public class OrdersPresenter extends Presenter {

    private OrdersView view;
    private OrdersModel model;

    public OrdersPresenter (OrdersView view) {
        this.view = view;
        model = new OrdersModel(this);
    }

    // metoda volaná z view, předává boolean modelu
    public void getOrders(boolean refresh) {
        model.getOrders(refresh);
    }

    // metoda bere list objednávek z modelu a předává je view
    public void setAllOrders(List<OrderData> orders) {
        view.setTableData(orders);
    }
}
