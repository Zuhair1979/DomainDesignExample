package com.assessor.filemanagement.exceptions;

import com.assessor.filemanagement.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseDto> handelAllApiExceptions(ApiException csvApiException, HttpServletRequest web){

        var responseBody=ErrorResponseDto.builder()
                .apiPath(web.getRequestURI())
                .timeStamp(LocalDateTime.now())
                .msg(csvApiException.getMsg())
                .status(csvApiException.getStatus())
                .build();

        return ResponseEntity.status(csvApiException.getStatus()).body(responseBody);
    }

    @ExceptionHandler(FileEmptyException.class)
    public ResponseEntity<Void> handleFileEmpty(FileEmptyException ex) {
        return ResponseEntity.noContent().build(); // 204
    }
}
