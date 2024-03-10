package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.entity.Entity;

public interface Controller {
    public void create(Entity entity);
    public ArrayList<Entity> list();
    public Entity getOne(HashMap params);
}
