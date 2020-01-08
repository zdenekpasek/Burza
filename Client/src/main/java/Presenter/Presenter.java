package Presenter;

import Services.SceneService;

public class Presenter {

    public void openWindow(String scene){
        try {
            SceneService.switchScene(scene);
        }catch (Exception e){
            System.out.println("e = " + e);

        }
    }

    public void error() {
        // TODO
    }
}
