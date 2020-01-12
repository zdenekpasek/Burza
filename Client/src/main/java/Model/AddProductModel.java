package Model;


import Presenter.AddProductPresenter;
import java.io.*;
import java.util.Arrays;
import Services.NetworkService;

// předává data addProductPresenteru, posílá zprávy serveru o operaci přidání produktu, včetně inputů od uživatele
public class AddProductModel {
    private AddProductPresenter presenter;

    // metoda převezme inputy uživatele z addProductPresenteru, jméno produktu, popis, cena, cesta k obrázku
    // z cesty k obrázku produktu vytvoří file a převede ne pole bytů, následně veškerá data pošle serveru
    // (obrázek jako objekt, ostatní data jako pole stringů), zároveň pošle zprávu serveru, že chce přidat produkt
    // metoda čeká na odpověd, další řízení se předává presenteru
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

    public AddProductModel(AddProductPresenter presenter){
        this.presenter = presenter;
    }
}
