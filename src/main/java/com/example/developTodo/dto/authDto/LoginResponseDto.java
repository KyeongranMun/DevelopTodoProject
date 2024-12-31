package com.example.developTodo.dto.authDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 응답 데이터를 담는 DTO 클래스
 * 로그인 성공 시 클라이언트에게 전달할 정보를 포함
 */
@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private Long id;
    private String username;
    private String email;
}
