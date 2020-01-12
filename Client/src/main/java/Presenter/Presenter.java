package Presenter;

import Services.SceneService;

// obecná třída presenter, která má metodu pro otevírání obrazovke
// veškeré presentery dědí od této třídy
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
