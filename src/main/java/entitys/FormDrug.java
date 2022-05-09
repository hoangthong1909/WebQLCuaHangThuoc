package entitys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "formDrug")
public class FormDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @OneToMany(mappedBy = "idForm")
    private List<Drug> FormList;

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

    public List<Drug> getFormList() {
        return FormList;
    }

    public void setFormList(List<Drug> formList) {
        FormList = formList;
    }
}