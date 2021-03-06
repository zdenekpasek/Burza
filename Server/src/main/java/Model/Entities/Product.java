package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

// namapovaná třída z databáze (Product) včetně vazeb
@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "productID", unique = true, nullable = false)
    private Integer productID;

    @Column(name = "productName", nullable = false, length = 30)
    private String productName;

    @Column(name = "productDescription", length = 100)
    private String productDescription;

    @Column(name = "productPrice", nullable = false)
    private Integer productPrice;

    @Column(name = "productSold")
    private String productSold;

    @Column(name = "productPhoto")
    private byte[] productPhoto;

    // vazba na uživatele
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private Users user;

    // vazba na kategorii
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryID")
    private Category category;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderProduct> orders = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product )) return false;
        return productID != null && productID.equals(((Product) o).getProductID());
    }

    @Override
    public int hashCode() {
        return this.productID;
    }

    public Product(Integer productID, String productName, String productDescription, Integer productPrice, byte[] productPhoto) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productPhoto = productPhoto;
    }


    public Product(String productName, Integer productPrice, String productDescription) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }
    public Product(String productName, Integer productPrice, String productDescription, byte[] productPhoto, Users user, Category category) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.user = user;
        this.category = category;
        this.productPhoto = productPhoto;
    }
}
