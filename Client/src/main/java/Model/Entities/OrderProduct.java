package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import static javax.persistence.GenerationType.*;


@Entity
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

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new OrderProductId(order.getOrderNumber(), product.getProductID());
    }
}
