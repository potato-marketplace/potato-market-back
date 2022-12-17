package com.mini.potatomarket.util.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "댓글을 찾을 수 없습니다."),
    DUPLICATE_RESOURCE(HttpStatus.BAD_REQUEST, "중복된 username입니다."),
    EXIST_USER(HttpStatus.BAD_REQUEST, "중복된 아이디입니다."),
    INVALID_AUTH_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다."),
    INVALID_USER(HttpStatus.BAD_REQUEST, "작성자만 삭제/수정할 수 있습니다.");

//    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
//    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
//    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "존재하지 않는 유저입니다."),
//
//    /* 404 NOT_FOUND : Resource를 찾을 수 없음 */
//    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다."),
//    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "로그아웃 한 유저입니다."),
//    NOT_FOLLOW(HttpStatus.NOT_FOUND, "팔로우 중이지 않습니다"),
//
//    /* 409 CONFLICT : Resource의 현재 상태와 충돌, 보통 중복된 데이터 존재 */
//
//    /* 500 INTERNAL_SERVER_ERROR */
//    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버가 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
