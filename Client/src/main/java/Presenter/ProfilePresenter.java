package Presenter;

import Model.ProfileModel;
import Services.UserService;
import View.ProfileView;

// třída příjmá data z profileView a předává je profileModelu
public class ProfilePresenter extends Presenter{

    private ProfileView view;
    private ProfileModel model;

    public ProfilePresenter (ProfileView view) {
        this.view = view;
        model = new ProfileModel(this);
        view.setUserProfile(UserService.username, UserService.email, UserService.adressCountry, UserService.adressCity, UserService.adressZip);
    }
}
