package it.cri.demo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<Entity> implements Service<Entity> {

    @Autowired
    private JpaRepository<Entity, Long> repository;

    @Override
    public List<Entity> getAll() {
        return repository.findAll();
    }

    @Override
    public Entity getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Entity save(Entity obj) {
        return repository.save(obj);
    }

    @Override
    @Transactional
    public void delete(Entity obj) {
        repository.delete(obj);
    }
}
