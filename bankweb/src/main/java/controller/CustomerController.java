package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.dao.CustomerDAO;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Entity;

public class CustomerController implements Controller {
    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    public void create(Entity entity) {

    }

    @Override
    public ArrayList<Entity> list() {
        ArrayList<Entity> result = this.customerDAO.findAll();
        return result;
    }

    @Override
    public Entity getOne(HashMap params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

}
