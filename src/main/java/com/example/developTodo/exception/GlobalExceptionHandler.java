package com.example.developTodo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 예외는 여기에서만! ExceptionHandler 애너테이션 달아서 해주기 TODO 글자 수 넘었을 때 예외 날리기
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 일치하는 데이터가 없을 때
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> findIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 비밀번호 검증 실패 시
    @ExceptionHandler(AuthorizeException.class)
    public ResponseEntity<String> updateAuthorizeException(AuthorizeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
