package it.cri.demo.service;

import it.cri.demo.entity.Volunteer;
import it.cri.demo.repository.VolunteerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerService extends AbstractService<Volunteer> {

}
