package Presenter;

import Model.MainModel;
import Model.ProductData;
import Presenter.Presenter;
import Services.SceneService;
import View.MainView;

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

    public void getAllProducts() {
        model.handleServerAction();
    }

    public void setAllProducts(List<ProductData> products) {
        view.setTableData(products);
    }
}
