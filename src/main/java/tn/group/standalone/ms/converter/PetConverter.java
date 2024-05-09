package tn.group.standalone.ms.converter;


import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import tn.group.standalone.ms.dto.PetDto;
import tn.group.standalone.ms.entity.Pet;

import java.util.Objects;

@Data(staticConstructor = "newInstance")
public class PetConverter implements Converter<Pet, PetDto> {

    @Override
    public PetDto convert(Pet pet) {
        if (Objects.isNull(pet)) {
            return null;
        }
        return PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .birthDate(pet.getBirthDate())
                .type(PetTypeConverter.newInstance().convert(pet.getPetType()))
                .owner(OwnerConverter.newInstance().convert(pet.getOwner()))
                .build();
    }
}
