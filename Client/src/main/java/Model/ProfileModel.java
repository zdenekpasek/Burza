package Model;

import Presenter.ProfilePresenter;

// model třída profilu, předává data presenteru
public class ProfileModel {
    private ProfilePresenter presenter;

    public ProfileModel(ProfilePresenter presenter){
        this.presenter = presenter;
    }
}
