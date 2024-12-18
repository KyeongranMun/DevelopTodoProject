package com.example.developTodo.controller;

import com.example.developTodo.dto.LoginRequestDto;
import com.example.developTodo.dto.LoginResponseDto;
import com.example.developTodo.dto.SignUpRequestDto;
import com.example.developTodo.dto.SignUpResponseDto;
import com.example.developTodo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    // 회원 생성 API
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto =
                authService.signUp(requestDto);

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request) {

        log.info("로그인 확인 1");
        LoginResponseDto responseDto = authService.login(requestDto.getEmail(), requestDto.getPw());

        log.info("로그인 확인 2");
        HttpSession session = request.getSession(true);
        session.setAttribute("loginStatus", responseDto);

        log.info("로그인 확인 3");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 로그아웃 API
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 쿠키 삭제...

        return new ResponseEntity<>("로그아웃 되었습니다", HttpStatus.OK);
    }
}
