package tn.group.standalone.ms.service;


import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import tn.group.standalone.ms.converter.OwnerConverter;
import tn.group.standalone.ms.converter.OwnerRequestConverter;
import tn.group.standalone.ms.converter.PetConverter;
import tn.group.standalone.ms.dto.OwnerDto;
import tn.group.standalone.ms.dto.OwnerRequest;
import tn.group.standalone.ms.entity.Owner;
import tn.group.standalone.ms.repository.OwnerRepository;
import tn.group.standalone.ms.repository.PetRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    public OwnerDto create(OwnerRequest ownerRequest) {
         Owner owner = OwnerRequestConverter.newInstance().convert(ownerRequest);
         Owner savedOwner = ownerRepository.save(owner);
        return OwnerConverter.newInstance().convert(savedOwner);
    }

    public List<OwnerDto> findAll() {
        List<Owner> owners = ownerRepository.findAll();
        List<OwnerDto> ownerDtos = owners.stream().map(owner -> OwnerConverter.newInstance().convert(owner)).collect(Collectors.toList());
        ownerDtos.forEach(ownerDto -> ownerDto.setPets(petRepository.findByOwnerId(ownerDto.getId()).stream().map(pet -> PetConverter.newInstance().convert(pet)).collect(Collectors.toSet())));
        return ownerDtos;
    }

    public OwnerDto findById(Long id) {
         Owner owner = ownerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(UUID.randomUUID(),"owner not found for id" + id));
        return OwnerConverter.newInstance().convert(owner);
    }

    public List<OwnerDto> findByFirstName(String firstName) {
        List<Owner> owners = ownerRepository.findByFirstNameIgnoreCase(firstName);
        return owners.stream().map(owner -> OwnerConverter.newInstance().convert(owner)).collect(Collectors.toList());
    }

}
