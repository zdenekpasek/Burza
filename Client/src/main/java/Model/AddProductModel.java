package Model;


import Presenter.AddProductPresenter;
import Presenter.BasketPresenter;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

import Services.NetworkService;

import javax.imageio.ImageIO;

public class AddProductModel {
    private AddProductPresenter presenter;

    public void addProduct(String productName, String productDescription, int productPrice, String productPhotoPath, String category) {
        File file = new File(productPhotoPath);
        byte[] bFile = new byte[(int) file.length()];
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(bFile));
        String[] productData = new String[]{productName, productDescription, Integer.toString(productPrice), category};

        System.out.println("Sending data to server");
        NetworkService.sendMessage(NetworkService.ADD_PRODUCT);
        NetworkService.sendMessage(productData);
        NetworkService.sendMessage(bFile);
        System.out.println("Waiting for server response");
        try{
            switch(NetworkService.readMessage()){
                case NetworkService.ADD_PRODUCT_SUCCESS: {
                    presenter.addProductSucessfull();
                    break;
                }
                case NetworkService.ADD_PRODUCT_FAIL: {
                    presenter.addProductFailed();
                    break;
                }
                default:
                    presenter.error();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


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
