package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Embeddable
public class OrderProductId implements Serializable {

    @Column(name = "orderNumber")
    private Integer orderNumber;

    @Column(name = "productID")
    private Integer productID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderProductId that = (OrderProductId) o;
        return Objects.equals(orderNumber, that.orderNumber) &&
                Objects.equals(productID, that.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productID);
    }

    public OrderProductId(Integer orderNumber, Integer productID) {
        this.orderNumber = orderNumber;
        this.productID = productID;
    }

}
