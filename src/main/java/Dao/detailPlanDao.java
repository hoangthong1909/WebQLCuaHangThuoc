package Dao;

import entitys.DetailPlan;

import javax.persistence.TypedQuery;
import java.util.List;

public class detailPlanDao extends AbstractDao<DetailPlan> implements DaoInterface<DetailPlan>{
    @Override
    public DetailPlan findById(Integer id) {
        return super.findById(DetailPlan.class,id);
    }

    @Override
    public List<DetailPlan> findAll() {
        return super.finAll(DetailPlan.class);
    }
    public int deleteList(List<DetailPlan> entitys) throws Exception{
        int i =0;
        try {
            this.em.getTransaction().begin();
            for (DetailPlan entity : entitys) {
                em.remove(em.contains(entity) ? entity : em.merge(entity));;
                i++;
            }
            this.em.getTransaction().commit();
            return i;
        }catch (Exception e){
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public List<DetailPlan> findByIDPlan(int id){
        String jpql="SELECT obj from DetailPlan obj where obj.idPlan.id= :id";
        TypedQuery<DetailPlan> query =this.em.createQuery(jpql,DetailPlan.class);
        query.setParameter("id",id);
        List<DetailPlan> list=query.getResultList();
        return list;
    }
}
