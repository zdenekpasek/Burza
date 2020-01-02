package Presenter;

import Model.ProfileModel;
import Services.SceneService;
import View.ProfileView;

public class ProfilePresenter {

    private ProfileView view;
    private ProfileModel model;

    public void openWindow(String scene){
        try {
            SceneService.switchScene(scene);
        }catch (Exception e){
            System.out.println("e = " + e);

        }
    }

    public ProfilePresenter (ProfileView view) {
        this.view = view;
        model = new ProfileModel(this);
    }
}
