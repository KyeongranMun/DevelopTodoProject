package com.example.developTodo.dto;

import lombok.Getter; // soft delete 유저만 삭제되고 일정은 남아있게 ->  cascade 비즈니스 로직으로도 처리 가능하다...

@Getter
public class UpdateTodoRequestDto {
    private String title;
    private String contents;
    private String pw;
}
