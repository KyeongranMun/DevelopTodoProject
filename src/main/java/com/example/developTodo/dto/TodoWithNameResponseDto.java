package com.example.developTodo.dto;

import lombok.Getter;

@Getter
public class TodoWithNameResponseDto {
    private final String title;
    private final String contents;
    private final String username;

    public TodoWithNameResponseDto(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}
