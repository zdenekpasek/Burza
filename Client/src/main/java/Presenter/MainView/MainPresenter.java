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

    public void openWindow(){
        model.writeToDB();
        try {
            SceneService.switchScene(SceneService.loginScreen);
        }catch (Exception e){

        }
    }

    public void showPopUp(String text){
        System.out.println(text);
    }

}
