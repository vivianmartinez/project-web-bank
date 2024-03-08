package controller;

import java.util.ArrayList;

import model.dao.CustomerDAO;
import model.entity.Entity;

public class CustomerController implements Controller {
    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    public void create(Entity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ArrayList<Entity> list() {
        ArrayList<Entity> result = this.customerDAO.findAll();
        return result;
    }

    @Override
    public ArrayList<Entity> getOne() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

}
