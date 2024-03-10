package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.dao.CustomerDAO;
import model.entity.Entity;
import utilities.Utilities;

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
        ArrayList<HashMap> find = this.customerDAO.findBy(params);
        if(find.size() > 0){
            return Utilities.customerFromHash(find.get(0));
        }
        return null;
    }

}
