package Dao;

import entitys.User;

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
    public User findByEmail(String email){
        String jpql = "SELECT o FROM User o Where o.email =?0";
        return super.findOne(User.class,jpql,email);
    }
    public List<User> findChuCH() {
        return super.findChuCH(User.class,1);
    }
    public User findByPhone(String phone){
        String jpql = "SELECT o FROM User o Where o.sdt =?0";
        return super.findOne(User.class,jpql,phone);
    }
    public List<User> findByUserCH(Integer id) {
        return super.findMany(User.class, "SELECT obj from User obj where obj.id= ?0", id);
    }
    public List<User> findByUserLock(Integer id) {
        return super.findMany(User.class, "SELECT obj from User obj where obj.userAdd= ?0", id);
    }
}
