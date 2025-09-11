package it.cri.demo.repository;

import it.cri.demo.entity.AssociativeFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociativeFeeRepository extends JpaRepository<AssociativeFee, Long> {
}
