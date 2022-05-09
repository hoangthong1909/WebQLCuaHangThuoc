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
    public List<Plan> findByIDChuCH(int id){
        String jpql="SELECT obj from Plan obj where obj.idCuaHang.id= :id AND obj.status=1 ";
        TypedQuery<Plan> query =this.em.createQuery(jpql,Plan.class);
        query.setParameter("id",id);
        List<Plan> list=query.getResultList();
        return list;
    }
}
