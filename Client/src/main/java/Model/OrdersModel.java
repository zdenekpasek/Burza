package Model;

import Presenter.OrdersPresenter;

public class OrdersModel {
    private OrdersPresenter presenter;

    public OrdersModel(OrdersPresenter presenter){
        this.presenter = presenter;
    }
}
