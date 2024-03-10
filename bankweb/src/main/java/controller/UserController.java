package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.dao.UserDAO;
import model.entity.Entity;
import model.entity.User;
import utilities.Utilities;

public class UserController implements Controller {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    @Override
    public void create(Entity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ArrayList<Entity> list() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    /**
     * Obtener un registro que cumpla con los parámetros de búsqueda
     * 
     * @Author Vivian Martínez
     * @return Entity
     * @param HashMap que contenga como key el nombre del campo y el valor que debe
     *                cumplir
     */
    @Override
    public Entity getOne(HashMap params) {
        ArrayList<HashMap> find = this.userDAO.findBy(params);
        return Utilities.userFromHash(find.get(0));
    }

}
