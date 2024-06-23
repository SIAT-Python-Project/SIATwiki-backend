package com.webserver.siatwiki.common.response.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getDetail())
                        .build(),
                errorCode.getHttpStatus());
    }

    public static String toString(ErrorCode errorCode) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(
                    ErrorResponse.builder()
                            .status(errorCode.getHttpStatus().value())
                            .error(errorCode.getHttpStatus().name())
                            .code(errorCode.name())
                            .message(errorCode.getDetail())
                            .build());
        } catch (JsonProcessingException e) {
            throw new CustomException(ErrorCode.JSON_TO_STRING_ERROR);
        }
    }
}