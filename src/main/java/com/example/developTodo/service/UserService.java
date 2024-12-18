package com.example.developTodo.service;

import com.example.developTodo.config.PasswordEncoder;
import com.example.developTodo.dto.DeleteUserRequestDto;
import com.example.developTodo.dto.UpdateUserRequestDto;
import com.example.developTodo.dto.UserResponseDto;
import com.example.developTodo.entity.User;
import com.example.developTodo.exception.AuthorizeException;
import com.example.developTodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    // 회원 삭제
    public void deleteUser(Long id, DeleteUserRequestDto deleteDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일치하는 회원을 찾을 수 없습니다."));

        // 입력받은 비밀번호와 저장된 비밀번호가 일치하는지 검증
        if (!passwordEncoder.matches(deleteDto.getPw(), user.getPw())) {
            throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(user);
    }
}

