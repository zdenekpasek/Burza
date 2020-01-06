package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "Order")
@Data
@NoArgsConstructor
public class Order implements Serializable{
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "orderNumber", unique = true, nullable = false)
    private Integer orderNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "orderDate", nullable = false)
    private Date orderDate;

    @Column(name = "orderStatus", nullable = false, length = 20)
    private String orderStatus;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderProduct> products = new ArrayList<>();

    public void addProduct(Product product){
        OrderProduct orderProduct = new OrderProduct(this, product);
        products.add(orderProduct);
        product.getOrders().add(orderProduct);
    }

//    public void removeProduct(Product product){
//        for (Iterator<OrderProduct> iterator = products.iterator();
//            iterator.hasNext();){
//            OrderProduct orderProduct = iterator.next();
//
//            if(orderProduct.getOrder().)
//        }
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order )) return false;
        return orderNumber != null && orderNumber.equals(((Order) o).getOrderNumber());
    }

    @Override
    public int hashCode() {
        return this.orderNumber;
    }

    public Order(Integer orderNumber, Date orderDate, String orderStatus) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public Order(Date orderDate, String orderStatus) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
