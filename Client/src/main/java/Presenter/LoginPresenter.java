package Presenter;

import Model.LoginModel;
import Services.SceneService;
import View.LoginView;

public class LoginPresenter {

    private LoginView view;
    private LoginModel model;

    public void openWindow(String scene){
        try {
            SceneService.switchScene(scene);
        }catch (Exception e){
            System.out.println("e = " + e);

        }
    }

    public LoginPresenter (LoginView view) {
        this.view = view;
        model = new LoginModel(this);
    }
}
