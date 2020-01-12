package Model;

import Presenter.LoginPresenter;
import Services.NetworkService;
import Services.UserService;

import java.io.IOException;

// předává data loginPresenteru, posílá zprávy serveru o operaci přihlášení uživatele, včetně inputů od uživatele
public class LoginModel {

    private LoginPresenter presenter;

    public LoginModel(LoginPresenter presenter){
        this.presenter = presenter;
    }

    // metoda převezme inputy uživatele z loginPresenteru, email uživatele a heslo
    // data pošle serveru ve formě pole Stringů, včetně zprávy, že se uživatel chce přihlásit
    // metoda čeká na odpověd, další řízení se předává presenteru
    // při úspěšném přihlášení uloží potřebná data uživatele v UserService (při každém přihlášení
    // se načtou data uživatele)
    public void loginUser(String email, String password) {
        String[] data = new String[]{email, password};
        NetworkService.sendMessage(NetworkService.LOGIN);
        NetworkService.sendMessage(data);
        try{
            switch(NetworkService.readMessage()){
                case NetworkService.LOGIN_SUCCESS: {
                    UserService.email = NetworkService.readMessage();
                    UserService.username = NetworkService.readMessage();
                    UserService.adressCity = NetworkService.readMessage();
                    UserService.adressZip = NetworkService.readMessage();
                    UserService.adressCountry = NetworkService.readMessage();

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
