package com.example.developTodo.auth.domain;

/**
 * 회원가입 응답 데이터를 담는 DTO 클래스
 * 회원가입 성공 시 클라이언트에게 전달할 정보를 포함
 */
public record SignUpResponseDto(
        Long id,
        String username,
        String email
) {
}
