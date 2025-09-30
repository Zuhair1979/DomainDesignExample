package com.assessor.filemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data@AllArgsConstructor
public class ResponseDto<T> {
    private String statusCode;
    private T responsebody;
}
