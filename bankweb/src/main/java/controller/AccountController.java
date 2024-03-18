package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.dao.AccountDAO;
import model.entity.Entity;
import utilities.Utilities;

public class AccountController implements Controller {

    AccountDAO accountDAO = new AccountDAO();


    public void create(Entity entity) {
        this.accountDAO.create(entity);
    }

    @Override
    public ArrayList<Entity> list() {
        ArrayList<Entity> result = this.accountDAO.findAll();
        return result;
    }

    @Override
    public Entity getOne(HashMap params) {
        ArrayList<HashMap> find = this.accountDAO.findBy(params);
        if (find.size() > 0) {
            // convierto el elemento obtenido del hash en entidad cuenta
            return Utilities.accountFromHash(find.get(0));
        }
        return null;
    }

    public ArrayList<HashMap> listJoin() {
        return this.accountDAO.findAllJoin();
    }

}
