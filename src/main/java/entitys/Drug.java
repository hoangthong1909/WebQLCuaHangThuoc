package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "term")
    private Integer term;

    @Column(name = "status")
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeDrug idType;
    @ManyToOne
    @JoinColumn(name = "form_id")
    private FormDrug idForm;

}