package Dao;

import entitys.Warehouse;

import java.util.List;

public class warehouseDao extends AbstractDao<Warehouse> implements DaoInterface<Warehouse>{
    @Override
    public Warehouse findById(Integer id) {
        return super.findById(Warehouse.class,id);
    }

    @Override
    public List<Warehouse> findAll() {
        return super.finAll(Warehouse.class);
    }
}
