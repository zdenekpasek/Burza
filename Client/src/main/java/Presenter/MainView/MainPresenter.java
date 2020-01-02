package Presenter.MainView;

import Model.MainModel;
import Services.SceneService;
import View.MainView;

public class MainPresenter {

    private MainView view;
    private MainModel model;

    public MainPresenter(MainView view){
        this.view = view;
        model = new MainModel(this);
    }

    public void openWindow(String scene){
        model.writeToDB();
        try {
            SceneService.switchScene(scene);
        }catch (Exception e){

        }
    }

    public void showPopUp(String text){
        System.out.println(text);
    }

}
