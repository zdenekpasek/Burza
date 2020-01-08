package Presenter;

import Model.RegisterModel;
import Services.SceneService;
import View.RegisterView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RegisterPresenter extends Presenter {

    private RegisterView view;
    private RegisterModel model;

    public void validUser(String email, String name, String adress, String password){
        if(view.checkEmail(email) && (view.checkName(name) && (view.checkPassword(password) && (view.checkAdress(adress))))){
            model.registerUser(email, name, adress, password);
            System.out.println("Valid input, user registered.");
        } else {
            System.out.println("Invalid input");
            view.checkName(name);
            view.checkPassword(password);
            view.checkAdress(adress);
        }

    }

    public RegisterPresenter (RegisterView view) {
        this.view = view;
        model = new RegisterModel(this);
    }

    public void registerFailed() {
        System.out.println("Register failed");
    }

    public void registerSuccesful() {
        try{
            SceneService.switchScene(SceneService.mainScreen);
        }catch(IOException e){
            System.out.println("Scene switch failed");
        }

    }
}
