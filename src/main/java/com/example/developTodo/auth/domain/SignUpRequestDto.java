package com.example.developTodo.auth.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

/**
 * 클라이언트가 회원가입을 요청할 때 전달해야 하는 데이터를 담은 DTO 클래스
 */
@Getter
public record SignUpRequestDto(
        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @Email
        String email,

        @NotBlank(message = "이름은 필수 입력값입니다.")
        @Pattern(
                regexp = "[a-zA-z가-힣],{1,12}",
                message = "이름은 영문자, 한글만 사용 가능하며 2자에서 12자 사이여야 합니다."
        )
        String username,
        String pw
       ) {
}
