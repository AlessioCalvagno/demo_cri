package it.cri.demo.repository;

import it.cri.demo.entity.Promotion;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends RelatedRepositoryInterface<Promotion> {
}
