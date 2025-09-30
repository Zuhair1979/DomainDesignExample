package com.assessor.filemanagement.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
public final class ErrorResponseDto {
    private final String apiPath;
    private final LocalDateTime timeStamp;
    private final String msg;
    private final HttpStatus status;

    public ErrorResponseDto(String apiPath, LocalDateTime timeStamp, String msg, HttpStatus status) {
        this.apiPath = apiPath;
        this.timeStamp = timeStamp;
        this.msg = msg;
        this.status = status;
    }

    public String getApiPath() {
        return apiPath;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ErrorResponseDto) obj;
        return Objects.equals(this.apiPath, that.apiPath) &&
                Objects.equals(this.timeStamp, that.timeStamp) &&
                Objects.equals(this.msg, that.msg) &&
                Objects.equals(this.status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiPath, timeStamp, msg, status);
    }

    @Override
    public String toString() {
        return "ErrorResponseDto[" +
                "apiPath=" + apiPath + ", " +
                "timeStamp=" + timeStamp + ", " +
                "msg=" + msg + ", " +
                "status=" + status + ']';
    }

}



// file is empty
// columns greater than 4 eg
