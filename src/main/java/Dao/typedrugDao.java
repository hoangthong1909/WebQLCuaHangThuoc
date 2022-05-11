package Dao;

import entitys.TypeDrug;

import java.util.List;

public class typedrugDao extends AbstractDao<TypeDrug> implements DaoInterface<TypeDrug> {
    @Override
    public TypeDrug findById(Integer id) {
        return super.findById(TypeDrug.class,id);
    }

    @Override
    public List<TypeDrug> findAll() {
        return super.finAll(TypeDrug.class);
    }
}
