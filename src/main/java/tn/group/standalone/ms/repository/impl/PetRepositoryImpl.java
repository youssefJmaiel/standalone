package tn.group.standalone.ms.repository.impl;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import tn.group.standalone.ms.entity.Pet;
import tn.group.standalone.ms.entity.Visit;
import tn.group.standalone.ms.repository.PetRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@AllArgsConstructor
@Repository
public class PetRepositoryImpl implements PetRepositoryCustom {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Page<Pet> search(Pageable pageable, String petName, List<Long> petTypeIds, List<Long> ownerIds, LocalDate minDateVisit, LocalDate maxDateVisit) {

        List<Predicate> predicates = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Pet> query = criteriaBuilder.createQuery(Pet.class);
        Root<Pet> root = query.from(Pet.class);

        Optional.ofNullable(petName).filter(not(String::isEmpty)).ifPresent(c -> predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), petName.toUpperCase())));

        Optional.ofNullable(petTypeIds).ifPresent(c -> predicates.add(root.get("petType").in(petTypeIds)));
        Optional.ofNullable(ownerIds).ifPresent(c -> predicates.add(root.get("owner").in(ownerIds)));

        Optional.ofNullable(minDateVisit).ifPresent(c -> {
            Join<Pet, Visit> visits = root.join("visits", JoinType.INNER);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(visits.get("date"), minDateVisit));
        });
        Optional.ofNullable(maxDateVisit).ifPresent(c -> {
            Join<Pet, Visit> visits = root.join("visits", JoinType.INNER);
            predicates.add(criteriaBuilder.lessThanOrEqualTo(visits.get("date"), maxDateVisit));
        });

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Pet> typedQuery = em.createQuery(query);
        List<Pet> resultList = typedQuery.getResultList();
        int totalRows = resultList.size();
        return new PageImpl<>(resultList, pageable, totalRows);
    }
}
