package tn.group.standalone.ms.converter;

import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import tn.group.standalone.ms.dto.SpecialtyDto;
import tn.group.standalone.ms.dto.VetDto;
import tn.group.standalone.ms.entity.Vet;

import java.util.Objects;
import java.util.stream.Collectors;

@Data(staticConstructor = "newInstance")
public class VetConverter implements Converter<Vet, VetDto> {
    @Override
    public VetDto convert(Vet vet) {
        if (Objects.isNull(vet)) {
            return null;
        }
        return VetDto.builder()
                .id(vet.getId())
                .firstName(vet.getFirstName())
                .lastName(vet.getLastName())
                .specialties(vet.getSpecialties().stream()
                        .map(specialty -> SpecialtyDto.builder()
                                .id(specialty.getId())
                                .description(specialty.getDescription())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
