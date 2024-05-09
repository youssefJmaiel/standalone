package tn.group.standalone.ms.converter;

import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import tn.group.standalone.ms.dto.PetTypeDto;
import tn.group.standalone.ms.entity.PetType;

import java.util.Objects;

@Data(staticConstructor = "newInstance")
public class PetTypeConverter implements Converter<PetType, PetTypeDto> {

    @Override
    public PetTypeDto convert(PetType petType) {
        if (Objects.isNull(petType)) {
            return null;
        }
        return PetTypeDto.builder()
                .id(petType.getId())
                .name(petType.getName())
                .build();
    }
}
