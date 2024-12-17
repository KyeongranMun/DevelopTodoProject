package com.example.developTodo.service;

import com.example.developTodo.config.PasswordEncoder;
import com.example.developTodo.dto.DeleteUserRequestDto;
import com.example.developTodo.dto.SignUpResponseDto;
import com.example.developTodo.dto.UpdateUserRequestDto;
import com.example.developTodo.dto.UserResponseDto;
import com.example.developTodo.entity.User;
import com.example.developTodo.exception.AuthorizeException;
import com.example.developTodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 유저 생성, 특정 유저 조회, 전체 유저 조회, 유저 정보 수정(작성자명, 이메일, 비밀번호, 비밀번호 검증), 유저 삭제(비밀번호 검증)

@Service
@RequiredArgsConstructor // 다른 클래스나 인터페이스의 생성자를 주입받기 위해 해당 애너테이션을 붙여준다.
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder 인스턴스 주입받음

    // 회원 생성
    public SignUpResponseDto signUp(String userName, String pw, String email) {
        // 비밀번호를 암호화하여 저장
        String encodePassword = passwordEncoder.encode(pw);

        // 암호화된 비밀번호로 User 객체 생성
        User user = new User(userName, encodePassword, email);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    // 특정 회원 조회
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 회원이 없습니다."));

        return new UserResponseDto(user);
    }

    // 전체 회원 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 회원 정보 수정
    public UserResponseDto updateUserInfo(Long id, UpdateUserRequestDto updateUserRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일치하는 회원을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(updateUserRequestDto.getPw(), user.getPw())) {
            throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
        }

        user.updateUserInfo(updateUserRequestDto.getUsername(), updateUserRequestDto.getEmail());
        User saveUser = userRepository.save(user);

        return new UserResponseDto(saveUser);
    }

    // 회원 삭제 Todo 회원 삭제 기능 작동 이상 일정 삭제해야 기능함. 수정 필요
    public void deleteUser(Long id, DeleteUserRequestDto deleteDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일치하는 회원을 찾을 수 없습니다."));

        // 입력받은 비밀번호와 저장된 비밀번호가 일치하는지 검증
        if (!passwordEncoder.matches(deleteDto.getPw(), user.getPw())) {
            throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(user);
    }
}

