package tn.group.standalone.ms.converter;

import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import tn.group.standalone.ms.dto.VetRequest;
import tn.group.standalone.ms.entity.Vet;

import java.util.Objects;

@Data(staticConstructor = "newInstance")
public class VetRequestConverter implements Converter<VetRequest, Vet> {
    @Override
    public Vet convert(VetRequest vetRequest) {
        if (Objects.isNull(vetRequest)) {
            return null;
        }
        return Vet.builder()
                .firstName(vetRequest.getFirstName())
                .lastName(vetRequest.getLastName())
                .build();

    }
}
