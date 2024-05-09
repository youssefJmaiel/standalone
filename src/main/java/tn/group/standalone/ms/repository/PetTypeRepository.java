package tn.group.standalone.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.group.standalone.ms.entity.PetType;


@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}
