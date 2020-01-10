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

    public void addProduct(String productName, String productDescription, int productPrice, String productPhotoPath) {
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
        String[] productData = new String[]{productName, productDescription, Integer.toString(productPrice), Arrays.toString(bFile)};

        System.out.println("Sending data to server");
        NetworkService.sendMessage(NetworkService.ADD_PRODUCT);
        NetworkService.sendMessage(productData);
        System.out.println("Waiting for server response");
        try{
            switch(NetworkService.readMessage()){
                case NetworkService.ADD_PRODUCT_SUCCESS: {

                    String test = Arrays.toString(bFile);
                    byte[] ripByte =new byte[test.length()];
                    int i = 0;
                    for(String testString : test.split(",")){
                        ripByte[i] = testString;
                    }


                    ByteArrayInputStream bis2 = new ByteArrayInputStream(bFile);
                    BufferedImage bImage3 = ImageIO.read(bis2);
                    ImageIO.write(bImage3, "png", new File("outputOriginal.png") );
                    System.out.println("image done");

            //        byte [] data = NetworkService.readMessage().getBytes();
            //        ByteArrayInputStream bis = new ByteArrayInputStream(data);
            //        BufferedImage bImage2 = ImageIO.read(bis);
            //        ImageIO.write(bImage2, "png", new File("output.png") );
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
