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

    @ManyToOne
    @MapsId("orderNumber")
    @JoinColumn(name = "orderNumber")
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productID")
    @JoinColumn(name = "productID")
    private Product product;

    @Column(name = "quantity")
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

    public OrderProduct(Orders order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new OrderProductId(order.getOrderNumber(), product.getProductID());
    }
}
