package it.cri.demo.repository;

import it.cri.demo.entity.Qualification;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends RelatedRepositoryInterface<Qualification> {
}
