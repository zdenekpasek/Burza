package Model;

import Presenter.RemoveProductPresenter;
import Services.NetworkService;

import java.io.IOException;

public class RemoveProductModel {
    private RemoveProductPresenter presenter;

    public RemoveProductModel(RemoveProductPresenter presenter){
        this.presenter = presenter;
    }

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
