package Presenter.MainView;

import Model.MainView.MainModel;
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
            SceneService.switchScene(SceneService.scene2);
        }catch (Exception e){

        }
    }

    public void showPopUp(String text){
        System.out.println(text);
    }

}
