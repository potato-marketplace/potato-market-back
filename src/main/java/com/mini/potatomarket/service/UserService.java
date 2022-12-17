package com.mini.potatomarket.service;

import com.mini.potatomarket.dto.SignupRequestDto;
import com.mini.potatomarket.entity.User;
import com.mini.potatomarket.repository.UserRepository;
import com.mini.potatomarket.util.error.CustomException;
import com.mini.potatomarket.util.jwt.JwtUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    public void signup(SignupRequestDto dto) {
        String loginId = dto.getLoginId();
        String password = passwordEncoder.encode(dto.getPassword());
        String nickname = dto.getNickname();

        if(userRepository.findByLoginId(loginId).isPresent()) {
            throw new IllegalArgumentException("아이디 중복");
        }

        if(userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("닉네임 중복");
        }

        User user = new User(loginId, password, nickname);
        userRepository.save(user);
    }

    // 로그인
    public void login(SignupRequestDto dto, HttpServletResponse response) {
        String loginId = dto.getLoginId();
        String password = passwordEncoder.encode(dto.getPassword());

        User user = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new IllegalArgumentException("아이디 중복")
        );

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getLoginId()));
    }

    // 아이디 중복 확인
    public void idCheck(SignupRequestDto dto) {
        String loginId = dto.getLoginId();

        if(userRepository.findByLoginId(loginId).isPresent()) {
            throw new IllegalArgumentException("아이디 중복");
        }
    }

    // 닉네임 중복 확인
    public void nickCheck(SignupRequestDto dto) {
        String nickname = dto.getNickname();

        if(userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("닉네임 중복");
        }
    }
}
