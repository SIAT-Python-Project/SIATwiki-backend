package com.webserver.siatwiki.common.advice;

import com.webserver.siatwiki.common.response.error.CustomException;
import com.webserver.siatwiki.common.response.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> noticeNoSuchElementExceptionHandler(CustomException e) {
        log.error(e.getMessage(), e);
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
