package Presenter;

import Model.RegisterModel;
import Services.SceneService;
import View.RegisterView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RegisterPresenter {

    private RegisterView view;
    private RegisterModel model;


    public void validUser(String email, String name, String adress, String password){
        if(view.checkEmail(email) && (view.checkName(name) && (view.checkPassword(password) && (view.checkAdress(adress))))){
            System.out.println("Valid input");
        } else {
            System.out.println("Invalid input");
            view.checkName(name);
            view.checkPassword(password);
            view.checkAdress(adress);
        }

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
