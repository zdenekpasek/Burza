package Presenter;

import Model.RegisterModel;
import Services.SceneService;
import View.RegisterView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RegisterPresenter extends Presenter {

    private RegisterView view;
    private RegisterModel model;

    public void validUser(String email, String name, String adress, String password){
        if(view.checkEmail(email) && (view.checkName(name) && (view.checkPassword(password) && (view.checkAdress(adress))))){
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
}
