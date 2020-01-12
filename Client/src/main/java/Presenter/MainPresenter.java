package Presenter;

import Model.MainModel;
import Model.ProductData;
import View.MainView;

import java.util.List;

// třída příjmá data z mainView a předává je mainModelu
public class MainPresenter extends Presenter {

    private MainView view;
    private MainModel model;

    public MainPresenter(MainView view){
        this.view = view;
        model = new MainModel(this);
    }

    // metoda volaná z view po stisknutí tlačítka refresh, předává boolean modelu
    public void getAllProducts(boolean refresh) {
        model.handleServerAction(refresh);
    }

    // metoda získá data o produktech z modelu a předá je view
    public void setAllProducts(List<ProductData> products) {
        view.setTableData(products);
    }

    // metoda volaná z view po stisknutí tlačítka buy, předá boolean modelu
    public boolean buyProduct(ProductData productData) {
       return model.buyProduct(productData);
    }

    // metoda volaná z view po stisknutí tlačítka showPicture, předá productID modelu
    public void showPicture(String productID) {
        model.showPicture(productID);
    }

    // předává obrázek produktu do view
    public void showPictureWindow(byte[] picture) {
        view.showPictureWindow(picture);
    }
}
