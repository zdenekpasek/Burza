package Model;

import Presenter.RegisterPresenter;
import Services.NetworkService;

import java.io.IOException;

public class RegisterModel {
    private RegisterPresenter presenter;

    public RegisterModel(RegisterPresenter presenter){
        this.presenter = presenter;
    }

    public void registerUser(String email, String name, String city, String country, String ZIP, String password) {
        String[] data = new String[]{email, name, city, country, ZIP, password};
        NetworkService.sendMessage(NetworkService.REGISTER);
        NetworkService.sendMessage(data);
        try{
            switch(NetworkService.readMessage()){
                case NetworkService.REGISTER_SUCCESS: {
                    presenter.registerSuccesful();
                    break;
                }
                case NetworkService.REGISTER_FAIL: {
                    presenter.registerFailed();
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
