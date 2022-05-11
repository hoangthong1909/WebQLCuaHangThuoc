package Dao;

import entitys.DetailedWarehouse;

import javax.persistence.TypedQuery;
import java.util.List;

public class detailedWarehouseDao extends AbstractDao<DetailedWarehouse> implements DaoInterface<DetailedWarehouse>{
    @Override
    public DetailedWarehouse findById(Integer id) {
        return super.findById(DetailedWarehouse.class,id);
    }

    @Override
    public List<DetailedWarehouse> findAll() {
        return super.finAll(DetailedWarehouse.class);
    }
    public List<DetailedWarehouse> findShop(Integer id){
        return super.findMany(DetailedWarehouse.class,"SELECT o FROM DetailedWarehouse o WHERE o.idWarehouse.idShop.id =?0",id);
    }
    public int deleteList(List<DetailedWarehouse> entitys) throws Exception {
        int i = 0;
        try {
            this.em.getTransaction().begin();
            for (DetailedWarehouse entity : entitys) {
                em.remove(em.contains(entity) ? entity : em.merge(entity));
                ;
                i++;
            }
            this.em.getTransaction().commit();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
    }
    public List<DetailedWarehouse> findByIDWarehouse(int id){
        String jpql="SELECT obj from DetailedWarehouse obj where obj.idWarehouse.id= :id";
        TypedQuery<DetailedWarehouse> query =this.em.createQuery(jpql,DetailedWarehouse.class);
        query.setParameter("id",id);
        List<DetailedWarehouse> list=query.getResultList();
        return list;
    }
}
