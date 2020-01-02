package Model;

import Presenter.ProfilePresenter;
import Presenter.RegisterPresenter;

public class ProfileModel {
    private ProfilePresenter presenter;

    public ProfileModel(ProfilePresenter presenter){
        this.presenter = presenter;
    }
}
