package tn.group.standalone.ms.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String errorKey;
    private String errorMessage;

}