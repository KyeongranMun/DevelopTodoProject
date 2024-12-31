package com.example.developTodo.dto.authDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 클라이언트가 로그인을 요청할 때 전달해야 하는 데이터를 담은 DTO 클래스
 */
@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String email;
    private String pw;
}
