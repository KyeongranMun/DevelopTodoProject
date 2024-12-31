package com.example.developTodo.dto.authDto;

import lombok.Getter;

/**
 * 회원가입 응답 데이터를 담는 DTO 클래스
 * 회원가입 성공 시 클라이언트에게 전달할 정보를 포함
 */
@Getter
public class SignUpResponseDto {

    private final Long id;

    private final String username;

    private final String email;

    public SignUpResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
