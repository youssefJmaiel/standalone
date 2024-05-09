package tn.group.standalone.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VetDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<SpecialtyDto> specialties;
}
