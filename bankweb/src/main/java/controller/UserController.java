package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.dao.UserDAO;
import model.entity.Entity;
import utilities.Utilities;

public class UserController implements Controller {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public void create(Entity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ArrayList<Entity> list() {
        ArrayList<Entity> result = this.userDAO.findAll();
        return result;
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
        if (find.size() > 0) {
            // convierto el elemento obtenido del hash en entidad cuenta
            return Utilities.userFromHash(find.get(0));
        }
        return null;
    }

}
