package com.webserver.siatwiki.common.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    CLIENT_BAD_REQUEST(BAD_REQUEST, "잘못된 요청"),
    INFO_NOT_FOUND(NOT_FOUND, "해당하는 id의 info가 없습니다."),;

    private final HttpStatus httpStatus;
    private final String detail;
}
