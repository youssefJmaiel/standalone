package tn.group.standalone.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.group.standalone.ms.error.CustomErrorMessages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {

    @NotNull(message = CustomErrorMessages.PARAMETER_MAY_NOT_BE_NULL)
    @NotEmpty(message = CustomErrorMessages.PARAMETER_MAY_NOT_BE_EMPTY)
    private String name;
    private LocalDate birthDate;
    private Long type;
    private Long owner;
}
