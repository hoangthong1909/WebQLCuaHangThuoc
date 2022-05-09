package Dao;

import entitys.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao extends AbstractDao<User> implements DaoInterface<User>{

    @Override
    public User findById(Integer id) {
        return super.findById(User.class,id);
    }

    @Override
    public List<User> findAll() {
        return super.findAllUser(User.class,1);
    }
    public List<User> findChuCH() {
        return super.findChuCH(User.class,1);
    }
    public User findByEmail(String email){
        String jpql = "SELECT o FROM User o Where o.email =?0";
        return super.findOne(User.class,jpql,email);
    }
    public User findByPhone(String phone){
        String jpql = "SELECT o FROM User o Where o.sdt =?0";
        return super.findOne(User.class,jpql,phone);
    }
}
