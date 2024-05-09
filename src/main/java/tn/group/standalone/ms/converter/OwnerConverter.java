package tn.group.standalone.ms.converter;


import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import tn.group.standalone.ms.dto.OwnerDto;
import tn.group.standalone.ms.entity.Owner;

import java.util.Objects;

@Data(staticConstructor = "newInstance")
public class OwnerConverter implements Converter<Owner, OwnerDto> {

    @Override
    public OwnerDto convert(Owner owner) {
        if (Objects.isNull(owner)) {
            return null;
        }
        return OwnerDto.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .address(owner.getAddress())
                .city(owner.getCity())
                .telephone(owner.getTelephone())
                //   .pets(owner.getPets().stream().map(pet -> PetDto.builder().build()).collect(Collectors.toSet()))
                .build();
    }
}
