package com.example.developTodo.dto;

import lombok.Getter;

/**
 * 특정 일정 조회 시 응답 데이터를 담는 DTO 클래스
 * 조회 성공 시 일정의 내용과 사용자 이름 정보를 함께 전달
 */
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
