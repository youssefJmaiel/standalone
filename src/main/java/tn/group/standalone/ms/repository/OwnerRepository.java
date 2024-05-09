package tn.group.standalone.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.group.standalone.ms.entity.Owner;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {

    List<Owner> findByFirstNameIgnoreCase (String name);

    @Query("SELECT o FROM Owner o where o.firstName = :firstName")
    Owner getOwnerByFirstName(@Param("firstName") String firstName);

    List<Owner>findOwnerByCityEqualsAndTelephoneStartingWith(String city,String tel);
    List<Owner>findOwnerByCityEquals(String city);
}
