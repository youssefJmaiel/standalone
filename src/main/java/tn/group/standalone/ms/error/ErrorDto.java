package tn.group.standalone.ms.error;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ErrorDto {

    private String url;
    private Integer code;
    private String message;

    private List<ErrorItemDto> errors = new ArrayList<>();

    public void addError(String key) {
        errors.add(new ErrorItemDto(null, key));
    }

}
