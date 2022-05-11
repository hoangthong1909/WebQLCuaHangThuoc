package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detailedWarehouse")
public class DetailedWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDrug")
    private Drug idDrug;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "DateAdded")
    private Date dateAdded;

    @Column(name = "DateEnd")
    private Date dateEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idWarehouse")
    private Warehouse idWarehouse;

    @Override
    public String toString() {
        return "DetailedWarehouse{}";
    }
}