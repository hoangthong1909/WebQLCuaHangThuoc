package Dao;

import org.apache.log4j.Logger;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public class AbstractDao<T> {
    public static final EntityManager em = JpaUtil.getEntityManager();

    public T findById(Class<T> tClass,Integer id){
        return this.em.find(tClass,id);
    }
    public List<T> finAll(Class<T> tClass,boolean exitsIsActive){
        String nameEntity = tClass.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(nameEntity).append(" o");
        if (exitsIsActive ==true){
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery query = this.em.createQuery(sql.toString(),tClass);
        return query.getResultList();
    }
    public List<T> finAll(Class<T> tClass){
        String nameEntity = tClass.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(nameEntity).append(" o");
        TypedQuery query = this.em.createQuery(sql.toString(),tClass);
        return query.getResultList();
    }
    public List<T> findAll(Class<T> clazz, Integer exitsisStatus) {
        String entityName = clazz.getSimpleName();// lấy tên của class
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (exitsisStatus == 1) {
            sql.append(" WHERE status !=0");
        }
        TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
        return query.getResultList();
    }
    public List<T> findAllUser(Class<T> clazz, Integer exitsisStatus) {
        String entityName = clazz.getSimpleName();// lấy tên của class
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (exitsisStatus == 1) {
            sql.append(" WHERE status !=0 AND isAdmin !=2");
        }
        TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
        return query.getResultList();
    }
    public List<T> findUser(Class<T> clazz, Integer exitsisStatus) {
        String entityName = clazz.getSimpleName();// lấy tên của class
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (exitsisStatus == 1) {
            sql.append(" WHERE isAdmin =2 AND status=1");
        }
        TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
        return query.getResultList();
    }
    public List<T> findChuCH(Class<T> clazz, Integer exitsisStatus) {
        String entityName = clazz.getSimpleName();// lấy tên của class
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (exitsisStatus == 1) {
            sql.append(" WHERE isAdmin =1 AND status=1");
        }
        TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
        return query.getResultList();
    }
    public List<T> findAll(Class<T> clazz, Integer exitsisStatus,int pageNumber,int pageSize){
        String entityName = clazz.getSimpleName();// getSimpleName() lấy tên của class
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (exitsisStatus==1){
            sql.append(" WHERE status = 1");
        }
        TypedQuery<T> query = this.em.createQuery(sql.toString(),clazz);
        query.setFirstResult((pageNumber-1)*pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
    public List<T> findAll(Class<T> clazz, boolean exitsIsActive,int pageNumber,int pageSize){
        String entityName = clazz.getSimpleName();// getSimpleName() lấy tên của class
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (exitsIsActive==true){
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery<T> query = this.em.createQuery(sql.toString(),clazz);
        query.setFirstResult((pageNumber-1)*pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
    public T findOne (Class<T> tClass,String sql,Object...params){
        TypedQuery query = this.em.createQuery(sql,tClass);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i,params[i]);
        }
        List<T> result = query.getResultList();
        if (result.isEmpty()){
            return null;
        }
        return result.get(0);
    }
    public List<T> findMany(Class<T> clazz, String sql, Object... params) {
        TypedQuery<T> query = this.em.createQuery(sql, clazz);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }
    //lấy list danh sách từ nhiều Table
    @SuppressWarnings("unchecked")
    public List<T> findManyByNativeQuery(String sql , Object...params){
        Query query = this.em.createNativeQuery(sql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
        return query.getResultList();
    }
    //dùng để dùng Stored Procedure (thủ tục)
    public List<T> callStored(String nameStored, Map<String, Object> params) {
        StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery(nameStored);
        params.forEach((key, value) -> query.setParameter(key, value)); //key là tên tham số chuyền vào,value là giá trị muốn chuyền vào
        return (List<T>) query.getResultList();
    }
    public T create(T entity) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(entity);
            this.em.getTransaction().commit();
            System.out.println("Thêm thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            System.out.println("không thể thêm đối tượng " + entity.getClass().getSimpleName() + " trong Database");
            throw new RuntimeException(e);
        }
    }

    public T update(T entity) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
            System.out.println("Update thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            System.out.println("Cannot update entity " + entity.getClass().getSimpleName() + " to Database");
            throw new RuntimeException(e);
        }
    }

    public T delete(T entity){
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
            System.out.println("Delete thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            System.out.println("Cannot delete entity " + entity.getClass().getSimpleName() + " to Database");
            throw new RuntimeException(e);
        }
    }
}
