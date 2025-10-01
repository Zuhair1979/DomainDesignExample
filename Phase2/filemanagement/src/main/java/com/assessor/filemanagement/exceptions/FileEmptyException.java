package com.assessor.filemanagement.exceptions;

import com.assessor.filemanagement.entity.ErrorCodes;
import org.springframework.http.HttpStatus;

public class FileEmptyException extends ApiException {

    public FileEmptyException(String filePath) {
        super(HttpStatus.NOT_FOUND, ErrorCodes.FILE_IS_EMPTY, " %s Either empty or wrong Path".formatted(filePath));
    }
}
