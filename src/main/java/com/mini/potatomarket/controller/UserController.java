package com.mini.potatomarket.controller;

import com.mini.potatomarket.dto.ResponseDto;
import com.mini.potatomarket.dto.SignupRequestDto;
import com.mini.potatomarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody @Valid SignupRequestDto dto) {
        userService.signup(dto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "회원가입 성공"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody SignupRequestDto dto, HttpServletResponse response) {
        userService.login(dto, response);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "로그인 성공"));
    }

    // 아이디 중복 확인
    @PostMapping("/idDupleCheck")
    public ResponseEntity<ResponseDto> idCheck(@RequestBody @Valid SignupRequestDto dto) {
        userService.idCheck(dto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "아이디 사용 가능"));
    }

    // 닉네임 중복 확인
    @PostMapping("/nickDupleCheck")
    public ResponseEntity<ResponseDto> nickCheck(@RequestBody SignupRequestDto dto) {
        userService.nickCheck(dto);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "닉네임 사용 가능"));
    }

}
