package Model;

import Presenter.OrdersPresenter;
import Services.NetworkService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdersModel {
    private OrdersPresenter presenter;

    static List<OrderData> list = new ArrayList<>();

    public OrdersModel(OrdersPresenter presenter){
        this.presenter = presenter;
    }

    public void getOrders(boolean refresh){
        if(list.isEmpty() || refresh){
            try{
                NetworkService.sendMessage(NetworkService.GET_ALL_ORDERS);
                List<String[]> ordersString = (List<String[]>)NetworkService.readObjectMessage();
                System.out.println(ordersString.size());
                list.clear();
                for(String[] order : ordersString){
                    list.add(new OrderData(order[0], order[1], order[2]));
                }
                switch(NetworkService.readMessage()){
                    case NetworkService.GET_ALL_ORDERS_SUCCESS: {
                        presenter.setAllOrders(list);
                        System.out.println("Success");
                        break;
                    }
                    default:
                        System.out.println("Failed");
                        presenter.error();
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }else{
            presenter.setAllOrders(list);
        }

    }

}
