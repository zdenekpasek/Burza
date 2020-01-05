package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import static javax.persistence.GenerationType.*;


@Entity
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

    public Category(Integer categoryID, String categoryName, String categoryDescription) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
