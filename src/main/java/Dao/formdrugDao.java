package Dao;

import entitys.FormDrug;

import java.util.List;

public class formdrugDao extends AbstractDao<FormDrug> implements DaoInterface<FormDrug> {
    @Override
    public FormDrug findById(Integer id) {
        return super.findById(FormDrug.class,id);
    }

    @Override
    public List<FormDrug> findAll() {
        return super.finAll(FormDrug.class);
    }
}
