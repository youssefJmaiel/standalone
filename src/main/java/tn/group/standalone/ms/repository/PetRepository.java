package tn.group.standalone.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.group.standalone.ms.entity.Pet;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long>, PetRepositoryCustom {

    List<Pet> findByOwnerId (Long id);


}
