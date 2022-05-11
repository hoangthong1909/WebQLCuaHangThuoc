package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detailPlan")
public class DetailPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlan")
    private Plan idPlan;

    @Cascade({org.hibernate.annotations.CascadeType.REFRESH})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDrug")
    private Drug idDrug;

    @Column(name = "quantity")
    private Integer quantity;

}