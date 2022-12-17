package com.mini.potatomarket.controller;

import com.mini.potatomarket.dto.ResponseDto;
import com.mini.potatomarket.dto.SignupRequestDto;
import com.mini.potatomarket.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public ResponseDto signup(@RequestBody @Valid SignupRequestDto dto) {  //@RequestBody 추후 제거
        userService.signup(dto);
        return new ResponseDto(200, "회원가입 성공");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseDto login(@RequestBody SignupRequestDto dto, HttpServletResponse response) {
        userService.login(dto, response);
        return new ResponseDto(200, "로그인 성공");
    }

    // 아이디 중복 확인
    @PostMapping("/idDupleCheck")
    public ResponseDto idCheck(@RequestBody @Valid SignupRequestDto dto) {
        userService.idCheck(dto);
        return new ResponseDto(200, "아이디 사용 가능");
    }

    // 닉네임 중복 확인
    @PostMapping("/nickDupleCheck")
    public ResponseDto nickCheck(@RequestBody SignupRequestDto dto) {
        userService.nickCheck(dto);
        return new ResponseDto(200, "닉네임 사용 가능");
    }

}
