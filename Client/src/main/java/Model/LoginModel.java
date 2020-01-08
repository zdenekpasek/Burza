package Model;

import Presenter.LoginPresenter;
import Services.NetworkService;

import java.io.IOException;


public class LoginModel {

    private LoginPresenter presenter;

    public LoginModel(LoginPresenter presenter){
        this.presenter = presenter;
    }

    public void loginUser(String email, String password) {
        String[] data = new String[]{email, password};
        NetworkService.sendMessage(NetworkService.LOGIN);
        NetworkService.sendMessage(data);
        try{
            switch(NetworkService.readMessage()){
                case NetworkService.LOGIN_SUCCESS: {
                    presenter.loginSuccessful();
                    break;
                }
                case NetworkService.LOGIN_FAIL: {
                    presenter.loginFailed();
                    break;
                }
                default:
                    presenter.error();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
