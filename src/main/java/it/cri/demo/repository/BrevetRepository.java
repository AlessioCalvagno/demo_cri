package it.cri.demo.repository;

import it.cri.demo.entity.Brevet;
import org.springframework.stereotype.Repository;

@Repository
public interface BrevetRepository extends RelatedRepositoryInterface<Brevet> {
}
