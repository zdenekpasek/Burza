package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "userID", unique = true, nullable = false)
    private Integer userID;

    @Column(name = "userEmail", nullable = false, length = 30)
    private String userEmail;

    @Column(name = "userName", nullable = false, length = 30)
    private String userName;

    @Column(name = "userPassword", nullable = false, length = 30)
    private String userPassword;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userID")
    private Set<Order> orders;


    public User(Integer userID, String userEmail, String userName, String userPassword) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
    }

}
