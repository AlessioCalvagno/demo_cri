package it.cri.demo.repository;


import it.cri.demo.entity.RelatedEntity;
import it.cri.demo.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * This interface is for all entities that are related to Volunteer entity, i.e. all entities except Volunteer.
 * It's used to declarte findAllByVolunteer() method
 * @param <Entity>
 */
@NoRepositoryBean
public interface RelatedRepositoryInterface<Entity extends RelatedEntity> extends JpaRepository<Entity, Long> {

    List<Entity> findAllByVolunteer(Volunteer v);


}
