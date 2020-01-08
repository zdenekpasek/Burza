package Presenter;

import Model.LoginModel;
import Services.SceneService;
import View.LoginView;

import java.io.IOException;

public class LoginPresenter extends Presenter {

    private LoginView view;
    private LoginModel model;

    public LoginPresenter (LoginView view) {
        this.view = view;
        model = new LoginModel(this);
    }

    public void loginSuccessful() {
        try{
            SceneService.switchScene(SceneService.mainScreen);
        }catch(IOException e){
            System.out.println("Scene switch failed");
        }

    }

    public void loginFailed() {
        System.out.println("Login failed");
    }
}
