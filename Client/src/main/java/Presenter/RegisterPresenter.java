package Presenter;

import Model.RegisterModel;
import Services.SceneService;
import View.RegisterView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RegisterPresenter {

    private RegisterView view;
    private RegisterModel model;


    public void registerUser(){

    }





    public void openWindow(String scene){
        try {
            SceneService.switchScene(scene);
        }catch (Exception e){
            System.out.println("e = " + e);

        }
    }

    public RegisterPresenter (RegisterView view) {
        this.view = view;
        model = new RegisterModel(this);
    }
}
