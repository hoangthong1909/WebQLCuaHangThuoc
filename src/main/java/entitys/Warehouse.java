package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idShop")
    private Shop idShop;

    @Column(name = "status")
    private Integer status;

    @Cascade({org.hibernate.annotations.CascadeType.REFRESH})
    @OneToMany(mappedBy = "idWarehouse")
    private List<DetailedWarehouse> entityList;

    @Override
    public String toString() {
        return "Warehouse{}";
    }
}