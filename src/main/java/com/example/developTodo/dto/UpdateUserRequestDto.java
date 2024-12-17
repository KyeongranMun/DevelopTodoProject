package com.example.developTodo.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private String username;
    private String email;
    private String pw; // 검증용 비밀번호
}
