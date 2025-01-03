package com.example.developTodo.user.domain;

import lombok.Getter;

/**
 * 유저 응답 데이터를 담는 DTO 클래스
 * 클라이언트에게 유저 정보를 전달할 때 사용
 */
@Getter
public class UserResponseDto {

    private final String username;

    private final String email;

    public UserResponseDto(User user) {
        username = user.getUsername();
        email = user.getEmail();
    }
}
