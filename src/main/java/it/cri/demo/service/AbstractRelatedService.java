package it.cri.demo.service;

import it.cri.demo.entity.RelatedEntity;
import it.cri.demo.entity.Volunteer;
import it.cri.demo.repository.RelatedRepositoryInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * This abstract class is for all entities that are related to Volunteer entity, i.e. all entities except Volunteer.
 * @param <Entity>
 */
public abstract class AbstractRelatedService<Entity extends RelatedEntity> extends AbstractService<Entity> {

    @Autowired
    private RelatedRepositoryInterface<Entity> relatedRepository;

    @Transactional
    public List<Entity> getAllByVolunteer(Volunteer v) {
        return this.relatedRepository.findAllByVolunteer(v);
    }
}
