package tn.group.standalone.ms.converter;


import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import tn.group.standalone.ms.dto.PetTypeRequest;
import tn.group.standalone.ms.entity.PetType;

import java.util.Objects;

@Data(staticConstructor = "newInstance")
public class PetTypeRequestConverter implements Converter<PetTypeRequest, PetType> {
    @Override
    public PetType convert(PetTypeRequest petTypeRequest) {
        if (Objects.isNull(petTypeRequest)) {
            return null;
        }
        return PetType.builder()
                .name(petTypeRequest.getName())
                .build();
    }
}
