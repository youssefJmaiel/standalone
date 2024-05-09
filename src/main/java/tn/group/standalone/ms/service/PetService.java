package tn.group.standalone.ms.service;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.group.standalone.ms.converter.PetConverter;
import tn.group.standalone.ms.converter.PetRequestConverter;
import tn.group.standalone.ms.dto.PetDto;
import tn.group.standalone.ms.dto.PetRequest;
import tn.group.standalone.ms.entity.Owner;
import tn.group.standalone.ms.entity.Pet;
import tn.group.standalone.ms.repository.OwnerRepository;
import tn.group.standalone.ms.repository.PetRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final PetRequestConverter petRequestConverter;
     private final OwnerRepository ownerRepository;


    public PetDto create(PetRequest petRequest) {
        if (Objects.nonNull(petRequest)) {
            //Use hibernate session: fillPet()
            Pet pet = petRequestConverter.convert(petRequest);
            Pet savedPet = petRepository.save(pet);
            return PetConverter.newInstance().convert(savedPet);
        } else {
            return PetDto.builder().build();
        }
    }

    public Page<PetDto> search(Pageable pageable, String petName, List<Long> petTypeIds, List<Long> ownerIds, LocalDate minDateVisit, LocalDate maxDateVisit) {
        Page<Pet> pagedPets = petRepository.search(pageable, petName, petTypeIds, ownerIds, minDateVisit, maxDateVisit);
        return pagedPets.map(pet -> PetConverter.newInstance().convert(pet));
    }

    public Optional<PetDto> getById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        return optionalPet.map(pet -> PetConverter.newInstance().convert(pet));
    }


   public List<PetDto> findAll(){
       List<Pet> pets = petRepository.findAll();
       List<PetDto> petDtos = pets.stream().map(pet -> PetConverter.newInstance().convert(pet)).collect(Collectors.toList());

       return petDtos;
   }

   public List<PetDto> getPerrByOwner(String city){
        List<Owner> owners=ownerRepository.findOwnerByCityEqualsAndTelephoneStartingWith( city,"027");
        List<PetDto> petDto= owners.stream().map(Owner::getPets).flatMap(pets -> pets.stream()).map(pets -> PetConverter.newInstance().convert(pets)).collect(Collectors.toList());
        return petDto;

   }

   public List<PetDto> getPetsByOwners(String city){
        List<Owner> owners = ownerRepository.findAll();

        return owners.stream().filter(owner -> owner.getCity().equals(city)&& owner.getTelephone().startsWith("1-617"))
                .collect(Collectors.toList())
                .stream().map(Owner::getPets).flatMap(Collection::stream)
                .map(pets -> PetConverter.newInstance().convert(pets)).collect(Collectors.toList());

   }

   public List<PetDto> getPetsByOwners2(String city){
        List<Pet> pets = petRepository.findAll();
        return pets.stream().filter(pet -> pet.getOwner().getCity().equals(city) && pet.getOwner().getTelephone().startsWith("1"))
                .collect(Collectors.toList())
                .stream().map(pet-> PetConverter.newInstance().convert(pet)).collect(Collectors.toList());

   }

    public List<PetDto> findByVisits(String typeVisit){
        return petRepository.findAll()
                .stream()
                .filter(pet -> pet.getVisits().stream()
                        .anyMatch(visit -> visit.getVet().getSpecialties()
                                .stream()
                                .anyMatch(specialty -> specialty.getDescription().toUpperCase().contains(typeVisit.toUpperCase()))))
                .map(p->PetConverter.newInstance().convert(p)).collect(Collectors.toList());
    }







}
