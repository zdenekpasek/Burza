package Presenter;

import Model.LoginModel;
import Services.SceneService;
import View.LoginView;
import View.RegisterView;

import java.io.IOException;

public class LoginPresenter extends Presenter {

    private LoginView view;
    private LoginModel model;

    public LoginPresenter (LoginView view) {
        this.view = view;
        model = new LoginModel(this);
    }

    public void getLoginData(String email, String password){
        model.loginUser(email,password);
    }

    public void loginSuccessful() {
        try{
            SceneService.switchScene(SceneService.mainScreen);
        }catch(IOException e){
            System.out.println("Scene switch to main screen failed");
        }
    }

    public void loginFailed() {
        System.out.println("Login failed");
        view.wrongCredentials();
    }
}
