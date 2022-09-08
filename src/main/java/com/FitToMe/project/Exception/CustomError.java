package com.FitToMe.project.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomError {

    // 401 Unauthorized
    NEED_LOGIN(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),
    NOT_FOUND_EMAIL(HttpStatus.UNAUTHORIZED, "존재하지 않는 이메일 계정입니다"),

    // 403 Forbidden
    NO_AUTHORITY_TO_DELETE_POST(HttpStatus.FORBIDDEN, "해당 게시글을 삭제할 수 있는 권한이 없습니다"),
    NO_AUTHORITY_TO_DELETE_COMMENT(HttpStatus.FORBIDDEN, "해당 댓글을 삭제할 수 있는 권한이 없습니다"),
    NO_AUTHORITY_TO_MODIFY_POST(HttpStatus.FORBIDDEN, "해당 게시글을 수정할 수 있는 권한이 없습니다"),
    NO_AUTHORITY_TO_MODIFY_COMMENT(HttpStatus.FORBIDDEN, "해당 댓글을 수정할 수 있는 권한이 없습니다"),

    // 404 Not Found
    POST_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다"),
    USER_NOT_EXIST(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다"),

    // 409 Conflict
    EMAIL_ALREADY_REGISTERED(HttpStatus.CONFLICT, "해당 email 계정은 이미 사용중입니다"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    CustomError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}