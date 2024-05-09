package tn.group.standalone.ms.error;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CustomErrorMessages extends CommonErrorMessages {

    public static final String PARAMETER_MAY_NOT_BE_NULL = "parameter.may.not.be.null";
    public static final String PARAMETER_MAY_NOT_BE_EMPTY = "parameter.may.not.be.empty";


    // *********** Custom error messages *********** //

    public static ErrorMessage parameter_may_not_be_null;
    public static ErrorMessage parameter_may_not_be_empty;


    @PostConstruct
    public void load() {
        parameter_may_not_be_null = ErrorRepository.build(PARAMETER_MAY_NOT_BE_NULL, "Parameter may not be null.");
        parameter_may_not_be_empty = ErrorRepository.build(PARAMETER_MAY_NOT_BE_EMPTY, "Parameter may not be empty.");
    }

}
