package com.mini.potatomarket.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    INVALID_AUTH_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다."),
    BAD_ID_PASSWORD(HttpStatus.BAD_REQUEST, "아이디나 비밀번호 패턴이 맞지 않습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "댓글을 찾을 수 없습니다."),
    INVALID_USER(HttpStatus.BAD_REQUEST, "작성자만 삭제/수정할 수 있습니다."),


    /* 409 CONFLICT : Resource의 현재 상태와 충돌, 보통 중복된 데이터 존재 */
    EXIST_USER(HttpStatus.CONFLICT, "중복된 아이디입니다."),
    EXIST_NICK(HttpStatus.CONFLICT, "중복된 닉네임입니다."),
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
