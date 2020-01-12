package Presenter;

import Model.LoginModel;
import Services.SceneService;
import View.LoginView;
import java.io.IOException;

// třída příjmá data z loginView a předává je loginModelu
public class LoginPresenter extends Presenter {

    private LoginView view;
    private LoginModel model;

    public LoginPresenter (LoginView view) {
        this.view = view;
        model = new LoginModel(this);
    }

    // získá user input login data z view a předá je presenteru
    public void getLoginData(String email, String password){
        model.loginUser(email,password);
    }

    // metoda, která se zavolá po úspěšném přihlášení uživatele
    // a přepne se scéna na mainScreen
    public void loginSuccessful() {
        try{
            SceneService.switchScene(SceneService.mainScreen);
        }catch(IOException e){
            System.out.println("Scene switch to main screen failed");
        }
    }

    // metoda, která se zavolá po neúspěšném přihlášení uživatele
    // vypíše chybu a zavolá metodu z view
    public void loginFailed() {
        System.out.println("Login failed");
        view.wrongCredentials();
    }
}
