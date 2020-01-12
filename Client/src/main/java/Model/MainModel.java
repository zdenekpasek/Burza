package Model;

import Presenter.MainPresenter;
import Services.NetworkService;
import sun.nio.ch.Net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainModel {

    private MainPresenter presenter;

    public MainModel(MainPresenter presenter){
        this.presenter = presenter;
    }

    public void handleServerAction(){
        try{
            NetworkService.sendMessage(NetworkService.GET_ALL_PRODUCTS);
            List<String[]> productStrings = (List<String[]>)NetworkService.readObjectMessage();
            System.out.println(productStrings.size());
            List<ProductData> products = new ArrayList<>();
            for(String[] product : productStrings){
                products.add(new ProductData(product[0], product[1], product[2], product[3], product[4]));
            }
            switch(NetworkService.readMessage()){
                case NetworkService.GET_ALL_PRODUCTS_SUCCESS: {
                    presenter.setAllProducts(products);
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
    }


    public boolean buyProduct(String productID) {
        try{
            NetworkService.sendMessage(NetworkService.BUY_PRODUCT);
            NetworkService.sendMessage(productID);
            if(NetworkService.readMessage().equals(NetworkService.BUY_PRODUCT_SUCCESS)){
                System.out.println("Success");
                return true;
            }else {
                System.out.println("Test Failed");
                presenter.error();
                return false;
             }
            } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void showPicture(String productID) {
        try{
            System.out.println("Getting picture");
            NetworkService.sendMessage(NetworkService.SHOW_PICTURE);
            NetworkService.sendMessage(productID);
            byte[] picture = (byte[]) NetworkService.readObjectMessage();
            System.out.println("Got picture");
            presenter.showPictureWindow(picture);
//            switch(NetworkService.readMessage()){
//                case NetworkService.BUY_PRODUCT_SUCCESS: {
//                    System.out.println("Success");
//                    break;
//                }
//                default:
//                    System.out.println("Failed");
//                    presenter.error();
//            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
