package com.example.developTodo.todo.domain;

import lombok.Getter;

/**
 * 클라이언트가 일정 수정을 요청할 때 전달해야 하는 데이터를 담은 DTO 클래스
 */
@Getter
public class UpdateTodoRequestDto {
    private String title;
    private String contents;
    private String pw;
}
