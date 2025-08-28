package it.cri.demo.service;

import it.cri.demo.entity.Volunteer;
import it.cri.demo.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository repository;

    public List<Volunteer> getAll() {
        return repository.findAll();
    }

    public Volunteer getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Volunteer save(Volunteer obj) {
        return repository.save(obj);
    }
}
