package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "Order")
@NaturalIdCache
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
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
            mappedBy = "Order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderProduct> products;

    @ManyToOne
    private User user;

    public Order(Integer orderNumber, Date orderDate, String orderStatus) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
