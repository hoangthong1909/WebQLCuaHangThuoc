package Dao;

import entitys.Drug;

import java.util.List;

public class drugDao extends AbstractDao<Drug> implements DaoInterface<Drug>{
    @Override
    public Drug findById(Integer id) {
        return super.findById(Drug.class,id);
    }

    @Override
    public List<Drug> findAll() {
        return super.findAll(Drug.class,1);
    }
}
