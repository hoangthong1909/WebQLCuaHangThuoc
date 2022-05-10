package Dao;

import entitys.Plan;
import entitys.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class planDao extends AbstractDao<Plan> implements DaoInterface<Plan>{
    @Override
    public Plan findById(Integer id) {
        return super.findById(Plan.class,id);
    }

    @Override
    public List<Plan> findAll() {
        return super.findAll(Plan.class,1);
    }
}
