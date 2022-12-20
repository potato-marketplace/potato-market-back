package com.mini.potatomarket.util.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {

//    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int statusCode;
//    private final String error;
//    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .statusCode(errorCode.getHttpStatus().value())
//                        .error(errorCode.getHttpStatus().name())
//                        .code(errorCode.name())
                        .message(errorCode.getDetail())
                        .build());
    }

}