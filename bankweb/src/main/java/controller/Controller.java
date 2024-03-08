package controller;

import java.util.ArrayList;
import model.entity.Entity;

public interface Controller {
    public void create(Entity entity);

    public ArrayList<Entity> list();

    public ArrayList<Entity> getOne();
}
