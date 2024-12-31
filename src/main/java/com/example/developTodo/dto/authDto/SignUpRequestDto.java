package com.example.developTodo.dto.authDto;

import lombok.Getter;

/**
 * 클라이언트가 회원가입을 요청할 때 전달해야 하는 데이터를 담은 DTO 클래스
 */
@Getter
public class SignUpRequestDto { // 회원가입 요청이 들어올 때 회원이름, 비밀번호, 이메일을 입력받음

    private final String username;

    private final String pw;

    private final String email;

    public SignUpRequestDto(String username, String pw, String email) {
        this.username = username;
        this.pw = pw;
        this.email = email;
    }
}
