package com.example.developTodo.controller;

import com.example.developTodo.dto.*;
import com.example.developTodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 일정 생성, 특정 일정 조회, 전체 일정 조회, 일정 내용 수정(제목, 내용, 비밀번호검증), 일정 삭제(비밀번호 검증)

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    // 일정 생성 API
    @PostMapping
    public ResponseEntity<TodoResponseDto> save(@RequestBody CreateTodoRequestDto createDto) {
        TodoResponseDto todoResponseDto =
                todoService.save(
                        createDto.getTitle(),
                        createDto.getContents(),
                        createDto.getUsername()
                );
        return new ResponseEntity<>(todoResponseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회 API
    @GetMapping // TODO 날짜 기준 조회 기능 추가 쿼리 dsl?
    public ResponseEntity<List<TodoResponseDto>> findAll() {
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        return new ResponseEntity<>(todoResponseDtoList, HttpStatus.OK);
    }

    // 특정 일정 조회 API
    @GetMapping("/{id}") // TODO 작성자 이름으로 조회 추가
    public ResponseEntity<TodoWithNameResponseDto> findTodoById(@PathVariable Long id) {
        TodoWithNameResponseDto responseDto = todoService.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    // 일정 내용 수정 API
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequestDto updateTodoRequestDto) {
        return new ResponseEntity<>(todoService.updateTodo(id, updateTodoRequestDto), HttpStatus.OK);
    }

    // 일정 삭제 API
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id, @RequestBody DeleteTodoRequestDto deleteTodoRequestDto) {
        todoService.deleteTodo(id, deleteTodoRequestDto);
    }
}
