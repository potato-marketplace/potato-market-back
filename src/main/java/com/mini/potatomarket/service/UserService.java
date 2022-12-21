package com.mini.potatomarket.service;

import com.mini.potatomarket.dto.ResponseDto;
import com.mini.potatomarket.dto.SignupRequestDto;
import com.mini.potatomarket.entity.User;
import com.mini.potatomarket.repository.UserRepository;
import com.mini.potatomarket.util.error.CustomException;
import com.mini.potatomarket.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import static com.mini.potatomarket.util.error.ErrorCode.*;


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
            throw new CustomException(EXIST_USER);
        }

        if(userRepository.findByNickname(nickname).isPresent()) {
            throw new CustomException(EXIST_NICK);
        }

        User user = new User(loginId, password, nickname);
        userRepository.save(user);
    }

    // 로그인
    public void login(SignupRequestDto dto, HttpServletResponse response) {
        String loginId = dto.getLoginId();
        String password = dto.getPassword();

        User user = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(INVALID_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getLoginId()));
    }

    // 아이디 중복 확인
    public ResponseDto idCheck(SignupRequestDto dto) {
        String loginId = dto.getLoginId();

        if(userRepository.existsByLoginId(loginId)) {
            return new ResponseDto(409, "아이디 중복");
        } else {
            return new ResponseDto(200, "아이디 사용 가능");
        }
    }

    // 닉네임 중복 확인
    public ResponseDto nickCheck(SignupRequestDto dto) {
        String nickname = dto.getNickname();

        if(userRepository.existsByNickname(nickname)) {
            return new ResponseDto(409, "닉네임 중복");
        } else {
            return new ResponseDto(200, "닉네임 사용 가능");
        }
    }
}
