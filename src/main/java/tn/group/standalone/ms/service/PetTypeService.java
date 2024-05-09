package tn.group.standalone.ms.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import tn.group.standalone.ms.converter.PetTypeConverter;
import tn.group.standalone.ms.converter.PetTypeRequestConverter;
import tn.group.standalone.ms.dto.PetTypeDto;
import tn.group.standalone.ms.dto.PetTypeRequest;
import tn.group.standalone.ms.entity.PetType;
import tn.group.standalone.ms.repository.PetTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PetTypeService {


    private final PetTypeRepository petTypeRepository;


    public PetTypeDto create(PetTypeRequest petTypeRequest) {
        PetType petType = PetTypeRequestConverter.newInstance().convert(petTypeRequest);
        PetType savedPetType = petTypeRepository.save(petType);
        return PetTypeConverter.newInstance().convert(savedPetType);
    }

    public Optional<PetTypeDto> getById(Long id) {
        Optional<PetType> optionalPetType = petTypeRepository.findById(id);
        return optionalPetType.map(petType -> PetTypeConverter.newInstance().convert(petType));
    }

    public List<PetTypeDto> getAllType() {
        List<PetType> typeList = petTypeRepository.findAll();
        return typeList.stream().map(petType -> PetTypeConverter.newInstance().convert(petType)).collect(Collectors.toList());
    }

    public PetTypeDto update(PetTypeRequest petTypeRequest, Long id) {
        Optional<PetType> optionalPetType = petTypeRepository.findById(id);
        return optionalPetType.map(petType -> {
            petType.setName(petTypeRequest.getName());
            PetType updatedPetType = petTypeRepository.save(petType);
            return PetTypeConverter.newInstance().convert(updatedPetType);
        }).orElseThrow(() -> new ResourceAccessException("Pet type with id = " + id + " not found"));
    }

    public void delete(Long id) {
        Optional<PetType> optionalPetType = petTypeRepository.findById(id);
        optionalPetType.ifPresent(petTypeRepository::delete);
    }

}
