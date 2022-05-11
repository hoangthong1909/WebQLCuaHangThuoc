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
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCuaHang")
    private Shop idCuaHang;

    @Column(name = "content", length = 200)
    private String content;

    @Column(name = "status")
    private Integer status;

    @Cascade({org.hibernate.annotations.CascadeType.REFRESH})
    @OneToMany(mappedBy = "idPlan")
    private List<DetailPlan> entityList;
}