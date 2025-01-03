package com.example.developTodo.auth.controller;

import com.example.developTodo.auth.domain.LoginRequestDto;
import com.example.developTodo.auth.domain.LoginResponseDto;
import com.example.developTodo.auth.domain.SignUpRequestDto;
import com.example.developTodo.auth.domain.SignUpResponseDto;
import com.example.developTodo.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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

    /**
     * 회원가입 API
     * @return 응답 DTO, 성공 시 201 CREATED 상태 코드
     */
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = authService.signUp(requestDto);

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    /**
     * 로그인 API
     * @param request HttpServletRequest 객체를 통해 세션 관리
     * @return 응답 DTO, 성공 시 200 OK 상태 코드
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {

        LoginResponseDto responseDto = authService.login(requestDto);

        HttpSession session = request.getSession(true);
        session.setAttribute("loginStatus", responseDto);

        return ResponseEntity.ok(responseDto);
    }

    /**
     * 로그아웃 API
     * @param request HttpServletRequest 객체를 통해 세션 정보에 접근
     * @return 성공 시 로그아웃 메시지와 200 OK 상태 코드 반환
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>("로그아웃 되었습니다", HttpStatus.OK);
    }
}
