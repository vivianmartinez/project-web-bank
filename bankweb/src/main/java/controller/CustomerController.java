package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import model.dao.CustomerDAO;
import model.entity.Account;
import model.entity.Entity;
import utilities.Utilities;
import validator.customexceptions.InvalidInsertSQLException;

public class CustomerController implements Controller {
    private CustomerDAO customerDAO = new CustomerDAO();

    public void create(Entity entityCustomer, Entity entityAccount) throws InvalidInsertSQLException, SQLException {
        Account account = (Account) entityAccount;
        this.customerDAO.create(entityCustomer, account);
    }

    @Override
    public ArrayList<Entity> list() {
        ArrayList<Entity> result = this.customerDAO.findAll();
        return result;
    }

    @Override
    public Entity getOne(HashMap params) {
        ArrayList<HashMap> find = this.customerDAO.findBy(params);
        if(find.size() > 0){
            return Utilities.customerFromHash(find.get(0));
        }
        return null;
    }

}
