package com.igreendata.account.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * IncorrectParameterException throws for incorrect parameters.
 *
 * @author Dulip Chandana
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
public class ServiceException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    /**
     * ServiceException
     *
     * @param resourceName
     * @param fieldName
     * @param fieldValue
     */
    public ServiceException(final String resourceName, final String fieldName, final Object fieldValue) {
        super(String.format("%s Service Exception . %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
