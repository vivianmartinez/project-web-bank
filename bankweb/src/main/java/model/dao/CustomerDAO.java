package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import config.PersistDDBB;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Entity;
import utilities.Utilities;
import validator.customexceptions.InvalidInsertSQLException;

public class CustomerDAO implements Dao {

    private PersistDDBB persistDDBB;
    private AccountDAO accountDAO;

    public CustomerDAO() {
        this.persistDDBB = new PersistDDBB();
        this.accountDAO = new AccountDAO();
    }

    
    public void create(Entity entity, Account account) throws InvalidInsertSQLException, SQLException {
        Customer customer = (Customer) entity;
        ArrayList<Entity> entities = new ArrayList<>();
        entities.add(customer);
        Account createAccount = this.accountDAO.generateAccount(account);
        entities.add(createAccount);

        int insertCustomerId = 0;
        ArrayList<Integer> idsInserts = new ArrayList<>();
    
        idsInserts = this.persistDDBB.executeTransactionEntities(entities);
        insertCustomerId = idsInserts.get(0);
        if (insertCustomerId != 0) {
            System.out.println("Cliente y cuenta creados con éxito");
        } else {
            System.out.println("Something bad happend. Couldn\'t create customer.");
        }
      
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

    @Override
    public ArrayList<HashMap> findBy(HashMap whereParamValue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOneBy'");
    }

}
