package com.example.developTodo.dto;

import com.example.developTodo.entity.User;
import lombok.Getter;

@Getter // API마다 넘겨받는 데이터의 모양이 다르다. 유지보수관점에서 생각해야 한다. DTO 만 보고도 어떤 데이터를 받을지 직관적으로 알 수 있다. 요청과 응답 모양이 ㅇ예쁘게!
public class UserResponseDto {

    private final String username;

    private final String email;

    public UserResponseDto(User user) {
        username = user.getUsername();
        email = user.getEmail();
    }
}
