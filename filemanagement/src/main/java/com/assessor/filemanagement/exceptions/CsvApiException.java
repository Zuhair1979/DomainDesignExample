package com.assessor.filemanagement.exceptions;

import com.assessor.filemanagement.entity.ErrorCodes;
import org.springframework.http.HttpStatus;

public class CsvApiException extends RuntimeException{

    private final HttpStatus status;
    private final ErrorCodes code;
    private final String msg;
    // three unified data i need it for all subclasses
    public CsvApiException(HttpStatus status, ErrorCodes code, String msg){
        super(msg);
        this.code=code;
        this.status=status;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorCodes getCode() {
        return code;
    }
}
