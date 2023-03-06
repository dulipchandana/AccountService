package com.igreendata.account.exception;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * ResourceNotFoundException throws no result in result set.
 * @author Dulip Chandana
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Data
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    /**
     * ResourceNotFoundException throws when there is no eliments in db
     * @param resourceName
     * @param fieldName
     * @param fieldValue
     */
    public ResourceNotFoundException( final String resourceName, final String fieldName, final Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
