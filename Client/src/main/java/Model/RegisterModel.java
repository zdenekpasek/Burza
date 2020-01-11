package Model;

import Presenter.RegisterPresenter;
import Services.NetworkService;
import Services.UserService;

import java.io.IOException;

public class RegisterModel {
    private RegisterPresenter presenter;

    public RegisterModel(RegisterPresenter presenter){
        this.presenter = presenter;
    }

    // metoda získá user inputy od registerPresenteru a pošle je ve formě pole stringů na server
    // pošle se jak celé pole, tak zpráva o tom, že uživatel žádá o registraci
    // dále očekává zpátky od serveru zprávu, zda registrace proběhla úspěšně nebo neúspěšně, podle
    // této zprávý volá funkce registerPresenteru
    public void registerUser(String email, String name, String city, String country, String ZIP, String password) {
        String[] data = new String[]{email, name, city, country, ZIP, password};
        NetworkService.sendMessage(NetworkService.REGISTER);
        NetworkService.sendMessage(data);
        try{
            switch(NetworkService.readMessage()){
                case NetworkService.REGISTER_SUCCESS: {
                    UserService.email = NetworkService.readMessage();
                    UserService.username = NetworkService.readMessage();
                    UserService.adressCity = NetworkService.readMessage();
                    UserService.adressZip = NetworkService.readMessage();
                    UserService.adressCountry = NetworkService.readMessage();
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
