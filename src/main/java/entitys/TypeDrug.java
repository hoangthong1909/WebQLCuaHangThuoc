package entitys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "typeDrug")
public class TypeDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @OneToMany(mappedBy = "idType")
    private List<Drug> DrugList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Drug> getDrugList() {
        return DrugList;
    }

    public void setDrugList(List<Drug> drugList) {
        DrugList = drugList;
    }
}