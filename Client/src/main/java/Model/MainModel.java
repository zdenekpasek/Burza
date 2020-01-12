package Model;

import Presenter.MainPresenter;
import Services.NetworkService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.nio.ch.Net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

// předává data mainPresenteru, posílá zprávy serveru o potřebných operacích
public class MainModel {

    private MainPresenter presenter;

    // uchovává produkty v listu produktů, aby se nemuseli pokaždé načítat z db
    static List<ProductData> list = new ArrayList<>();

    public MainModel(MainPresenter presenter){
        this.presenter = presenter;
    }

    // metoda pošle serveru zprávu o tom, že chce získat veškeré produkty burzy
    // produkty získá ze serveru a naplní list, který předá presenteru
    // pokud jsou data již načtena, vezme produkty z paměti, pokud ne, z databáze
    public void handleServerAction(boolean refresh){
        if(list.isEmpty() || refresh){
            try{
                NetworkService.sendMessage(NetworkService.GET_ALL_PRODUCTS);
                List<String[]> productStrings = (List<String[]>)NetworkService.readObjectMessage();
                System.out.println(productStrings.size());
                list.clear();
                for(String[] product : productStrings){
                    list.add(new ProductData(product[0], product[1], product[2], product[3], product[4]));
                }
                switch(NetworkService.readMessage()){
                    case NetworkService.GET_ALL_PRODUCTS_SUCCESS: {
                        presenter.setAllProducts(list);
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
            presenter.setAllProducts(list);
        }

    }

    // pošle serveru zprávu, že chce uživatel koupit produkt podle productID
    // productID pošlu serveru jak String
    // jakmile klient dostane zprávu zpět, vrací true po úspěšném nákupu a naopak
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

    // pošle zprávu serveru, že chce zobrazit obrázek produktu podle productID
    // jakmile server pošle odpověď s obrázkem, klient obrázek přijme a předá presenteru
    public void showPicture(String productID) {
        try{
            System.out.println("Getting picture");
            NetworkService.sendMessage(NetworkService.SHOW_PICTURE);
            NetworkService.sendMessage(productID);
            byte[] picture = (byte[]) NetworkService.readObjectMessage();
            System.out.println("Got picture");
            presenter.showPictureWindow(picture);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
