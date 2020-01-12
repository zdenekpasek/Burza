package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.*;

// namapovaná třída z databáze (Orders) včetně vazeb
@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
public class Orders implements Serializable{
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "orderNumber", unique = true, nullable = false)
    private Integer orderNumber;

    @Column(name = "orderDate", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "orderStatus", nullable = false, length = 20)
    private String orderStatus;


    public void addProduct(Product product){
        OrderProduct orderProduct = new OrderProduct(this, product);
        //products.add(orderProduct);
        product.getOrders().add(orderProduct);
    }

    @ManyToOne(fetch = FetchType.LAZY)
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

    public Orders(Integer orderNumber, LocalDateTime orderDate, String orderStatus) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public Orders(LocalDateTime orderDate, String orderStatus, Users user) {
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.user = user;
    }


}
