package tn.group.standalone.ms.converter;


import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import tn.group.standalone.ms.dto.OwnerRequest;
import tn.group.standalone.ms.entity.Owner;

import java.util.Objects;

@Data(staticConstructor = "newInstance")
public class OwnerRequestConverter implements Converter<OwnerRequest, Owner> {

    @Override
    public Owner convert(OwnerRequest ownerRequest) {
        if (Objects.isNull(ownerRequest)) {
            return null;
        }
        return Owner.builder()
                .firstName(ownerRequest.getFirstName())
                .lastName(ownerRequest.getLastName())
                .address(ownerRequest.getAddress())
                .city(ownerRequest.getCity())
                .telephone(ownerRequest.getTelephone())
                .build();
    }
}
