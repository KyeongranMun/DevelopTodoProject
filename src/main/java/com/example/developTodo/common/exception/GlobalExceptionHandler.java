package com.example.developTodo.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    // 이메일 중복 여부 확인
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<String> emailDuplicateException(DuplicateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
