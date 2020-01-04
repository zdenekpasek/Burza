package Presenter;

import Model.ProfileModel;
import Services.SceneService;
import View.ProfileView;

public class ProfilePresenter extends Presenter{

    private ProfileView view;
    private ProfileModel model;

    public ProfilePresenter (ProfileView view) {
        this.view = view;
        model = new ProfileModel(this);
    }
}
