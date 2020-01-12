package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

// namapovaná třída z databáze (Users) včetně vazeb
@Entity(name = "Users")
@Table(name = "Users")
@Data
@NoArgsConstructor
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "userID", unique = true, nullable = false)
    private Integer userID;

    @Column(name = "userEmail", nullable = false, length = 30)
    private String userEmail;

    @Column(name = "userName", nullable = false, length = 30)
    private String userName;

    @Column(name = "userPassword", nullable = false, length = 30)
    private String userPassword;

    // vazba na adresu
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adress> adresses = new ArrayList<>();

    // vazba na product
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    // vazba na objednávku
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<Product> products = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<Order> orders = new ArrayList<>();



//    public void addAdress(Adress adress){
//        adresses.add((adress));
//        adress.setUsers(this);
//    }
//
//    public void removeAdress(Adress adress){
//        adresses.remove(adress);
//        adress.setUsers(null);
//    }

    public void addProduct(Product product){
        products.add(product);
        product.setUser(this);
    }

    public void removeProduct(Product product){
        products.remove(product);
        product.setUser(null);
    }

    public void addOrder(Orders order){
        orders.add(order);
        order.setUser(this);
    }


    public Users(String userEmail, String userName, String userPassword) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
    }


}
