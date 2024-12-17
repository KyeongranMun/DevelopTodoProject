package com.example.developTodo.dto;

import com.example.developTodo.entity.Todo;
import lombok.Getter;

@Getter
public class TodoResponseDto {
    private Long id;
    private final String title;
    private final String contents;


    public TodoResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public TodoResponseDto(Todo todo) {
        id = todo.getId();
        title = todo.getTitle();
        contents = todo.getContents();
    }

    public static TodoResponseDto toDto(Todo todo) {
        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getContents());
    }
}
