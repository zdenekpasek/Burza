package Presenter;

import Model.MainModel;
import Model.ProductData;
import Presenter.Presenter;
import Services.SceneService;
import View.MainView;
import javafx.collections.ObservableList;

import java.util.List;


public class MainPresenter extends Presenter {

    private MainView view;
    private MainModel model;

    public MainPresenter(MainView view){
        this.view = view;
        model = new MainModel(this);
    }

    public void showPopUp(String text){
        System.out.println(text);
    }

    public void getAllProducts(boolean refresh) {
        model.handleServerAction(refresh);
    }

    public void setAllProducts(List<ProductData> products) {
        view.setTableData(products);
    }

    public boolean buyProduct(String productID) {
       return model.buyProduct(productID);
    }

    public void showPicture(String productID) {
        model.showPicture(productID);
    }

    public void showPictureWindow(byte[] picture) {
        view.showPictureWindow(picture);
    }
}
