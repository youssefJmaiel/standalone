package tn.group.standalone.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.group.standalone.ms.entity.Vet;

public interface VetRepository extends JpaRepository<Vet, Long> {
}
