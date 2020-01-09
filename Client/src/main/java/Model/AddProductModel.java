package Model;


import Presenter.AddProductPresenter;
import Presenter.BasketPresenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import Services.NetworkService;

public class AddProductModel {
    private AddProductPresenter presenter;


    private void handleServerAction(PrintWriter out, BufferedReader in) throws IOException {
        while(true){
            String message = in.readLine();
            if(message.equals(NetworkService.CATEGORY_SEND)){
                String categories = in.readLine();
                System.out.println(categories);
                }

            }
        }


    public AddProductModel(AddProductPresenter presenter){
        this.presenter = presenter;
    }
}
