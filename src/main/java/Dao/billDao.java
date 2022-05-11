package Dao;

import entitys.Bill;

import java.util.List;

public class billDao extends AbstractDao<Bill> implements DaoInterface<Bill>{
    @Override
    public Bill findById(Integer id) {
        return super.findById(Bill.class,id);
    }

    @Override
    public List<Bill> findAll() {
        return super.finAll(Bill.class);
    }

    public List<Bill> findShop(Integer id){
        return super.findMany(Bill.class,"SELECT o FROM Bill o WHERE o.idShop.id =?0",id);
    }
}
