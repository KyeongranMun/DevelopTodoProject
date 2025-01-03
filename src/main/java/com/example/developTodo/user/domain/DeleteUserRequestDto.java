package com.example.developTodo.user.domain;

import lombok.Getter;

/**
 * 클라이언트가 유저 삭제를 요청할 때 전달해야 하는 데이터를 담은 DTO 클래스
 */
@Getter
public class DeleteUserRequestDto {
    private String pw;
}
