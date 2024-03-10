package model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import config.PersistDDBB;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Entity;
import utilities.Utilities;

public class AccountDAO implements Dao {

    private PersistDDBB persistDDBB;

    public AccountDAO() {
        this.persistDDBB = new PersistDDBB();
    }

    @Override
    public void create(Entity entity) {
        Account account = new Account();
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ArrayList<Entity> findAll() {
        String sqlQuery = "SELECT * FROM account ORDER BY id DESC";
        ArrayList<Entity> aEntity = new ArrayList<>();
        // convierto el array de hashMap en array de entidades
        ArrayList<HashMap> result = this.persistDDBB.executeSelectSQL(sqlQuery);
        // convert values of array to entity
        for (int i = 0; i < result.size(); i++) {
            Account a = Utilities.accountFromHash(result.get(i));
            aEntity.add(a);
        }
        return aEntity;
    }

    public ArrayList<HashMap> findAllJoin(){
        String sqlQuery = "SELECT *,c.dni,c.name,c.last_name,tc.type_account  FROM account a INNER JOIN customer c ON c.id = a.customer_id INNER JOIN type_account tc ON tc.id = a.type_account_id ORDER BY a.id DESC";
        return this.persistDDBB.executeSelectSQL(sqlQuery);
    }

    @Override
    public Entity findOne(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public ArrayList<HashMap> findBy(HashMap whereParamValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOneBy'");
    }

}
