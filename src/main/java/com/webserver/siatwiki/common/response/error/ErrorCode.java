package com.webserver.siatwiki.common.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // BAD_REQUEST: 400
    CLIENT_BAD_REQUEST(BAD_REQUEST, "잘못된 요청"),
    CATEGORY_NAME_NOT_FOUND(BAD_REQUEST, "해당하는 카테고리의 이름은 존재하지 않습니다."),

    // NOT_FOUND: 404
    INFO_NOT_FOUND(NOT_FOUND, "해당하는 id의 info가 없습니다."),
    PERSON_NOT_FOUND(NOT_FOUND, "해당하는 id의 person이 없습니다."),
    PROFILE_NOT_FOUND(NOT_FOUND, "해당하는 id의 profile이 없습니다."),
    USER_NOT_FOUND(NOT_FOUND, "해당하는 id의 user가 없습니다."),

    // INTERNAL_SERVER_ERROR: 500
    PROFILE_SAVE_FAIL(INTERNAL_SERVER_ERROR, "프로필 사진 저장에 실패하였습니다."),
    PROFILE_DELETE_FAIL(INTERNAL_SERVER_ERROR, "프로필 사진 삭제에 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}