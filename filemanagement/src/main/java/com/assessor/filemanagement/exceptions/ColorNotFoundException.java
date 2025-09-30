package com.assessor.filemanagement.exceptions;

import com.assessor.filemanagement.entity.ErrorCodes;
import org.springframework.http.HttpStatus;

public class ColorNotFoundException extends CsvApiException{

    public ColorNotFoundException(String color) {
        super(HttpStatus.NOT_FOUND, ErrorCodes.COLOR_NOT_FOUND, " %s Color not found Or no Person like it ".formatted(color));
    }
}
