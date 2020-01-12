package Model;

import Presenter.OrdersPresenter;
import Services.NetworkService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// předává data ordersPresenteru, slouží pro práci s objednávkami
public class OrdersModel {
    private OrdersPresenter presenter;

    // list pro uchovávání objednávek
    static List<OrderData> list = new ArrayList<>();

    public OrdersModel(OrdersPresenter presenter){
        this.presenter = presenter;
    }

    // metoda pošle serveru zprávu o tom, že chce získat objednávky
    // produkty získá ze serveru a naplní list, který předá presenteru
    // pokud jsou data již načtena, vezme produkty z paměti, pokud ne, z databáze
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
