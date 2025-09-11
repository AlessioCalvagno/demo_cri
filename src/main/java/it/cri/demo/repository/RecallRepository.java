package it.cri.demo.repository;

import it.cri.demo.entity.Recall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecallRepository extends JpaRepository<Recall, Long> {
}
