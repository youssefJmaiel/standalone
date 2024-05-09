package tn.group.standalone.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.group.standalone.ms.entity.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
}
