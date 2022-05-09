package Dao;

import entitys.DetailedWarehouse;

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
}
