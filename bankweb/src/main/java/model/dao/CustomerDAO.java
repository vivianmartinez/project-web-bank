package model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import config.PersistDDBB;
import model.entity.Customer;
import model.entity.Entity;
import utilities.Utilities;

public class CustomerDAO implements Dao {

    private PersistDDBB persistDDBB;

    public CustomerDAO() {
        this.persistDDBB = new PersistDDBB();
    }

    @Override
    public void create(Entity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ArrayList<Entity> findAll() {
        String sqlQuery = "SELECT * FROM customer";
        ArrayList<Entity> aEntity = new ArrayList<>();
        // convierto el array de hashMap en array de entidades
        ArrayList<HashMap> result = this.persistDDBB.executeSelectSQL(sqlQuery);
        // convert values of array to entity
        for (int i = 0; i < result.size(); i++) {
            Customer c = Utilities.customerFromHash(result.get(i));
            aEntity.add(c);
        }
        return aEntity;
    }

    @Override
    public Entity findOne(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

}
