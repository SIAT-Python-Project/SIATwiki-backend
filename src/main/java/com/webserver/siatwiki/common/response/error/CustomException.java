package com.webserver.siatwiki.common.response.error;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Throwable cause;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
        this.cause = null;
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getDetail(), cause);
        this.errorCode = errorCode;
        this.cause = cause;
    }
}