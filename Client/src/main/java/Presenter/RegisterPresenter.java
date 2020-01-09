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

    // zkontroluje, zda veškeré inputy potřebné pro registraci usera odpovídají požadovanému formátu, pokud ano inputy se
    // předají dále registerModelu - modelu už předáváme zahashované heslo, pokud podmínka selže, dojde opět k validaci
    public void validUser(String email, String name, String city, String country, String ZIP, String password){
        if(view.checkEmail(email) && (view.checkName(name) && (view.checkPassword(password) && (view.checkAdress(city, country, ZIP))))){
            model.registerUser(email, name, city, country, ZIP, view.hashPassword(password));
            System.out.println("Valid input, user registered.");
        } else {
            System.out.println("Invalid input");
            view.checkName(name);
            view.checkPassword(password);
            view.checkAdress(city, country, ZIP);
        }

    }

    public RegisterPresenter (RegisterView view) {
        this.view = view;
        model = new RegisterModel(this);
    }

    // metoda, která se provede po neúspěšné registraci
    public void registerFailed() {
        System.out.println("Register failed");
    }

    // metoda, která se provede po úspěšné registraci - změní scénu na mainScreen
    public void registerSuccesful() {
        try{
            SceneService.switchScene(SceneService.mainScreen);
        }catch(IOException e){
            System.out.println("Scene switch failed");
        }

    }
}
