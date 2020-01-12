package Model;

import Presenter.RemoveProductPresenter;
import Services.NetworkService;

import java.io.IOException;

// předává data modelPresenteru, posílá zprávy serveru o odstranění produktu z burzy
public class RemoveProductModel {
    private RemoveProductPresenter presenter;

    public RemoveProductModel(RemoveProductPresenter presenter){
        this.presenter = presenter;
    }

    // metoda dostane z removePresenteru jméno produktu, které chce uživatle odstranit
    // jméno produktu včetně zprávy o odstranění pošle serveru
    // server pošle zprávu o úspěšném nebo neúspěšném odstranění a metoda předá řízení presenteru
    public void removeProduct(String productName) {
        String data = productName;
        NetworkService.sendMessage(NetworkService.REMOVE_PRODUCT);
        NetworkService.sendMessage(data);
        try{
            switch(NetworkService.readMessage()){
                case NetworkService.REMOVE_PRODUCT_SUCCESS: {
                    presenter.removeProductSuccessfull();
                    break;
                }
                case NetworkService.REMOVE_PRODUCT_FAIL: {
                    presenter.removeProductFailed();
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
