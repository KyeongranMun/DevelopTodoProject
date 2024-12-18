package com.example.developTodo.dto;

import com.example.developTodo.entity.Todo;
import lombok.Getter;

/**
 * 일정 응답 데이터를 담는 DTO 클래스
 * 클라이언트에게 일정 정보를 전달할 때 사용
 */
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

    /**
     * 정적 팩토리 메서드 : Todo 엔티티를 DTO로 변환해 TodoResponseDto 객체를 간결하게 생성할 수 있도록 제공
     * @param todo Todo 엔티티 객체
     * @return TodoResponse 객체
     */
    public static TodoResponseDto toDto(Todo todo) {
        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getContents());
    }
}
