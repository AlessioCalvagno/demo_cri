package it.cri.demo.repository;

import it.cri.demo.entity.Brevet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrevetRepository extends JpaRepository<Brevet, Long> {
}
