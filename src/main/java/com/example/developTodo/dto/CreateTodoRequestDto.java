package com.example.developTodo.dto;

import lombok.Getter;

/**
 * 클라이언트가 일정 생성을 요청할 때 전달해야 하는 데이터를 담은 DTO 클래스
 */
@Getter
public class CreateTodoRequestDto {
    private final String title;
    private final String contents;
    private final String username;


    public CreateTodoRequestDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
