package com.assessor.filemanagement.exceptions;

import com.assessor.filemanagement.entity.ErrorCodes;
import org.springframework.http.HttpStatus;

public class PersonNotFoundException extends ApiException {
    public PersonNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND, ErrorCodes.PERSON_NOT_EXIST, " %s Person Id is not found ".formatted(id));
    }
}
