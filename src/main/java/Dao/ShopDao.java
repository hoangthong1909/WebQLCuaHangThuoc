package Dao;

import entitys.Shop;

import java.util.List;

public class ShopDao extends AbstractDao<Shop> implements DaoInterface<Shop>{
    @Override
    public Shop findById(Integer id) {
        return super.findById(Shop.class,id);
    }

    @Override
    public List<Shop> findAll() {
        return super.findAll(Shop.class,1);
    }
    public Shop findByIDchuCH(Integer id) {
        return super.findOne(Shop.class, "SELECT obj from Shop obj where obj.idChuCH.id= ?0", id);
    }
}
