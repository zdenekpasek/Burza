package Model;

import Presenter.MainPresenter;
import Services.NetworkService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class MainModel {

    private MainPresenter presenter;

    public MainModel(MainPresenter presenter){
        this.presenter = presenter;
    }

    public void handleServerAction(ObjectInputStream objIn, ObjectOutputStream objOut){
        try{
            switch(NetworkService.readMessage()){
                case NetworkService.SEND_ALL_PRODUCTS: {
                    presenter.getAllProducts();
                    break;
                }
                default:
                    presenter.error();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
