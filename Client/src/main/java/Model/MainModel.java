package Model;

import Presenter.MainView.MainPresenter;

public class MainModel {

    private MainPresenter presenter;

    public MainModel(MainPresenter presenter){
        this.presenter = presenter;
    }

    public void writeToDB() {
        System.out.println("Write to db");
        presenter.showPopUp("Text");
    }
}
