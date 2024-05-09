package tn.group.standalone.ms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import tn.group.standalone.ms.entity.Pet;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PetRepositoryCustom {
    Page<Pet> search(Pageable pageable, String petName, List<Long> petTypeIds, List<Long> ownerIds, LocalDate minNumberVisits, LocalDate maxNumberVisits);
}
