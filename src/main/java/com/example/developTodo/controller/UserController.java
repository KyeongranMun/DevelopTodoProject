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

/**
 * 유저 관리 컨트롤러 클래스
 * - 유저 조회, 수정, 삭제와 관련된 API 제공
 * - URL 기본 경로 : "/users"
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 특정 회원 조회 API, 성공 시 응답 DTO와 200 OK 상태 코드 반환
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto responseDto = userService.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    // 전체 회원 조회 API, 성공 시 응답 DTO리스트 반환
    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers().stream().map(UserResponseDto::new).toList();
    }

    // 회원 정보 수정 API, 성공 시 응답 DTO와 200 OK 상태 코드 반환
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserInfo(@PathVariable Long id, @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        return new ResponseEntity<>(userService.updateUserInfo(id, updateUserRequestDto),HttpStatus.OK);
    }

    // 회원 삭제 API, 비밀번호 검증이 필수
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id, @RequestBody DeleteUserRequestDto deleteDto) {
        userService.deleteUser(id, deleteDto);
    }
}
