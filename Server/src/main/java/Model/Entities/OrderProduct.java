package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity(name = "OrderProduct")
@Table(name = "OrderProduct")
@Data
@NoArgsConstructor
public class OrderProduct implements Serializable {

    @EmbeddedId
    private OrderProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderNumber")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productID")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderProduct that = (OrderProduct) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new OrderProductId(order.getOrderNumber(), product.getProductID());
    }
}
