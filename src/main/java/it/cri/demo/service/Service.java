package it.cri.demo.service;

import java.util.List;

public interface Service<Entity> {

    List<Entity> getAll();

    Entity getById(Long id);

    Entity save(Entity obj);
    void delete(Entity obj);

}
