package com.example.developTodo.dto;

import lombok.Getter;

@Getter
public class CreateTodoRequestDto {
    private final String title;
    private final String contents;
    private final String username; // 어떤 회원이 작성했는지 요청 정보에 담겨있어야 한다 -> 쿠키, 세션, 토큰 사용가능


    public CreateTodoRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
