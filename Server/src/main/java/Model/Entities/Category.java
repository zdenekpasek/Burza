package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.*;


@Entity(name = "Category")
@Table(name = "Category")
@Data
@NoArgsConstructor
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "categoryID", unique = true, nullable = false)
    private Integer categoryID;

    @Column(name = "categoryName", nullable = false, length = 30)
    private String categoryName;

    @Column(name = "categoryDescription", nullable = false, length = 30)
    private String categoryDescription;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "category",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product){
        products.remove(product);
        product.setCategory(null);
    }

    public Category(Integer categoryID, String categoryName, String categoryDescription) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }


    public Category(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
