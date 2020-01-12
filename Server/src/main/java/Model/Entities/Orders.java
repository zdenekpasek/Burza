package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity(name = "Orders")
@Table(name = "Orders")
@Data
@NoArgsConstructor
public class Orders implements Serializable{
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private Users user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Orders)) return false;
        return orderNumber != null && orderNumber.equals(((Orders) o).getOrderNumber());
    }

    @Override
    public int hashCode() {
        return this.orderNumber;
    }

    public Orders(Integer orderNumber, Date orderDate, String orderStatus) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public Orders(Date orderDate, String orderStatus, Users user) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.user = user;
    }


}
