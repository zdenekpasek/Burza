package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
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

    public Product(Integer productID, String productName, String productDescription, Integer productPrice, byte[] productPhoto) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productPhoto = productPhoto;
    }
}
