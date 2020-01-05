package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;

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

    @Column(name = "productPhoto")
    private byte[] productPhoto;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
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
}