package com.example.developTodo.common.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}