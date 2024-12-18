package com.example.developTodo.controller;

import com.example.developTodo.dto.DeleteUserRequestDto;
import com.example.developTodo.dto.UpdateUserRequestDto;
import com.example.developTodo.dto.UserResponseDto;
import com.example.developTodo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 유저 생성, 특정 유저 조회, 전체 유저 조회, 유저 정보 수정(작성자명, 이메일, 비밀번호, 비밀번호 검증), 유저 삭제(비밀번호 검증)

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 특정 회원 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto responseDto = userService.findById(id);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 회원 조회 API
    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream().map(UserResponseDto::new).toList();
    }

    // 회원 정보 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserInfo(@PathVariable Long id, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        return new ResponseEntity<>(userService.updateUserInfo(id, updateUserRequestDto),HttpStatus.OK);
    }

    // 회원 삭제 API
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id, @RequestBody DeleteUserRequestDto deleteDto) {
        userService.deleteUser(id, deleteDto);
    }
}
