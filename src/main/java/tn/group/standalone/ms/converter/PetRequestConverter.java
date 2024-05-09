package tn.group.standalone.ms.converter;


import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tn.group.standalone.ms.dto.PetRequest;
import tn.group.standalone.ms.entity.Pet;
import tn.group.standalone.ms.repository.OwnerRepository;
import tn.group.standalone.ms.repository.PetTypeRepository;

import java.util.Objects;


@Component
@Data(staticConstructor = "newInstance")
public class PetRequestConverter implements Converter<PetRequest, Pet> {
    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;

    @Override
    public Pet convert(PetRequest petRequest) {
        if (Objects.isNull(petRequest)) {
            return null;
        }
        return Pet.builder()
                .name(petRequest.getName())
                .birthDate(petRequest.getBirthDate())
                .petType(petTypeRepository.findById(petRequest.getType()).orElse(null))
                .owner(ownerRepository.findById(petRequest.getOwner()).orElse(null))
                .build();
    }
}
