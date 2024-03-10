package model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import model.entity.Entity;

public interface Dao {
    public void create(Entity entity);
    public ArrayList<Entity> findAll();
    public Entity findOne(int id);
    public ArrayList<HashMap> findBy(HashMap whereParamValue);
}
