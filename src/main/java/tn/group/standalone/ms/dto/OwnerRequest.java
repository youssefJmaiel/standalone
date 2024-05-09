package tn.group.standalone.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.group.standalone.ms.error.CustomErrorMessages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerRequest {

    @NotNull(message = CustomErrorMessages.PARAMETER_MAY_NOT_BE_NULL)
    @NotEmpty(message = CustomErrorMessages.PARAMETER_MAY_NOT_BE_EMPTY)
    private String firstName;
    @NotNull(message = CustomErrorMessages.PARAMETER_MAY_NOT_BE_NULL)
    @NotEmpty(message = CustomErrorMessages.PARAMETER_MAY_NOT_BE_EMPTY)
    private String lastName;
    private String address;
    private String city;
    private String telephone;
}
