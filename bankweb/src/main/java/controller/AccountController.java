package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.dao.AccountDAO;
import model.entity.Entity;

public class AccountController implements Controller {

    AccountDAO accountDAO = new AccountDAO();

    @Override
    public void create(Entity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ArrayList<Entity> list() {
        ArrayList<Entity> result = this.accountDAO.findAll();
        return result;
    }

    @Override
    public Entity getOne(HashMap params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOne'");
    }

}
