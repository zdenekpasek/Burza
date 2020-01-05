package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class OrderProductId implements Serializable {

    @Column(name = "orderNumber")
    private Integer orderNumber;

    @Column(name = "productID")
    private Integer productID;

    public OrderProductId(Integer orderNumber, Integer productID) {
        this.orderNumber = orderNumber;
        this.productID = productID;
    }
}
