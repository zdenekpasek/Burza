package Presenter;

import Model.LoginModel;
import Services.SceneService;
import View.LoginView;

public class LoginPresenter extends Presenter {

    private LoginView view;
    private LoginModel model;

    public LoginPresenter (LoginView view) {
        this.view = view;
        model = new LoginModel(this);
    }
}
