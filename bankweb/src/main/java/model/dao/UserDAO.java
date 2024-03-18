package model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import config.PersistDDBB;
import model.entity.Customer;
import model.entity.Entity;
import model.entity.User;
import utilities.Utilities;

public class UserDAO implements Dao {
    private PersistDDBB persistDDBB;

    public UserDAO() {
        this.persistDDBB = new PersistDDBB();
    }

   
    public void create(Entity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ArrayList<Entity> findAll() {
        String sqlQuery = "SELECT * FROM user";
        ArrayList<Entity> aEntity = new ArrayList<>();
        // convierto el array de hashMap en array de entidades
        ArrayList<HashMap> result = this.persistDDBB.executeSelectSQL(sqlQuery);
        // convert values of array to entity
        for (int i = 0; i < result.size(); i++) {
            User u = Utilities.userFromHash(result.get(i));
            aEntity.add(u);
        }
        return aEntity;
    }

    @Override
    public Entity findOne(int id) {
        User user = new User();
        HashMap where = new HashMap<>();
        where.put("id", id);
        ArrayList<HashMap> data = this.persistDDBB.executeSelectSQLPrepared(user, where, "*");
        if (data.size() > 0) {
            return Utilities.userFromHash(data.get(0));
        }
        return null;
    }

    @Override
    public ArrayList<HashMap> findBy(HashMap whereParamValue) {
        User user = new User();
        HashMap where = new HashMap<>();
        ArrayList<HashMap> data = this.persistDDBB.executeSelectSQLPrepared(user, whereParamValue, "*");
        return data;
    }

}
