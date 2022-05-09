package Dao;

import entitys.DetailBill;

import java.util.List;

public class detailBillDao extends AbstractDao<DetailBill> implements DaoInterface<DetailBill>{
    @Override
    public DetailBill findById(Integer id) {
        return super.findById(DetailBill.class,id);
    }

    @Override
    public List<DetailBill> findAll() {
        return super.finAll(DetailBill.class);
    }
}
