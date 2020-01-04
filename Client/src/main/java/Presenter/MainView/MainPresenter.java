package Presenter.MainView;

import Model.MainModel;
import Presenter.Presenter;
import Services.SceneService;
import View.MainView;

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

}
