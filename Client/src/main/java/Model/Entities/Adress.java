package Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "Adress")
@Data
@NoArgsConstructor
public class Adress implements Serializable{

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "adressNumber", unique = true, nullable = false)
    private Integer adressNumber;

    @Column(name = "adressCity", nullable = false, length = 30)
    private String adressCity;

    @Column(name = "adressZIP", nullable = false, length = 30)
    private String adressZIP;

    @Column(name = "adressCountry", nullable = false, length = 30)
    private String adressCountry;

    public Adress(Integer adressNumber, String adressCity, String adressZIP, String adressCountry) {
        this.adressNumber = adressNumber;
        this.adressCity = adressCity;
        this.adressZIP = adressZIP;
        this.adressCountry = adressCountry;
    }
}
