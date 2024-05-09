package tn.group.standalone.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto {
    private Long id;
    private LocalDate date;
    private String description;
    private PetDto petInfo;
}
