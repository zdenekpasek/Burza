package View;

import Presenter.Presenter;
import Services.SceneService;

import java.io.IOException;

public class View {

    private Presenter presenter;

    public void changeScene(String scene){
        try {
            SceneService.switchScene(scene);
        } catch (IOException e) {
            System.out.println("Scene switch failed");
        }
    }

    public void goToProfileScreen(){
       changeScene("profileScreen.fxml");
    }
    public void goToMainScreen(){
        changeScene("mainScreen.fxml");
    }
    public void goToOrdersScreen(){
        changeScene("ordersScreen.fxml");
    }

    public void goToAddProductScreen(){
        changeScene("addProductScreen.fxml");
    }

    public void goToRemoveProductScreen(){
        changeScene("removeProductScreen.fxml");
    }

    public void logOut() {
       changeScene("loginScreen.fxml");
    }
}
