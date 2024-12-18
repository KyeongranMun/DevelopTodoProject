package com.example.developTodo.service;

import com.example.developTodo.config.PasswordEncoder;
import com.example.developTodo.dto.LoginRequestDto;
import com.example.developTodo.dto.LoginResponseDto;
import com.example.developTodo.dto.SignUpRequestDto;
import com.example.developTodo.dto.SignUpResponseDto;
import com.example.developTodo.entity.User;
import com.example.developTodo.exception.AuthorizeException;
import com.example.developTodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 생성
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        // 비밀번호를 암호화하여 저장
        String encodePassword = passwordEncoder.encode(requestDto.getPw());

        // 암호화된 비밀번호로 User 객체 생성
        User userInput = new User(
                requestDto.getUsername(),
                encodePassword,
                requestDto.getEmail()
        );

        User savedUser = userRepository.save(userInput);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    // 로그인
    public LoginResponseDto login(LoginRequestDto requestDto) {

        User user = userRepository.findUsersByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(requestDto.getPw(), user.getPw())) {
            throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
