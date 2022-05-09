package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Integer dateAdded;

    @Column(name = "DateEnd")
    private Integer dateEnd;

    @Column(name = "status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idWarehouse")
    private Warehouse idWarehouse;


}