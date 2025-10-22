package it.cri.demo.repository;

import it.cri.demo.entity.MedicalVisit;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalVisitRepository extends RelatedRepositoryInterface<MedicalVisit> {
}
