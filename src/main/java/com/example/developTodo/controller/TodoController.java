package com.example.developTodo.controller;

import com.example.developTodo.dto.todoDto.*;
import com.example.developTodo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정 관리 컨트롤러 클래스
 * - 일정 생성, 조회, 수정, 삭제와 관련된 API 제공
 * - URL 기본 경로 : "/todos"
 * - 유저의 id(PK)를 FK로 참조
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    // 일정 생성 API, 성공 시 응답 DTO와 201 CREATED 상태 코드 반환
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

    // 전체 일정 조회 API, 성공 시 응답 DTO 리스트와 200 OK 상태 코드 반환
    @GetMapping // TODO 날짜 기준 조회 기능 추가 쿼리 dsl?
    public ResponseEntity<List<TodoResponseDto>> findAll() {
        List<TodoResponseDto> todoResponseDtoList = todoService.findAll();
        return ResponseEntity.ok(todoResponseDtoList);
    }

    // 특정 일정 조회 API, 성공 시 응답 DTO와 200 OK 상태 코드 반환
    @GetMapping("/{id}") // TODO 작성자 이름으로 조회 추가
    public ResponseEntity<TodoWithNameResponseDto> findTodoById(@PathVariable Long id) {
        TodoWithNameResponseDto responseDto = todoService.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    // 일정 내용 수정 API, 성공 시 응답 DTO와 200 OK 상태 코드 반환
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequestDto updateTodoRequestDto) {
        return new ResponseEntity<>(todoService.updateTodo(id, updateTodoRequestDto), HttpStatus.OK);
    }

    // 일정 삭제 API, 비밀번호 검증이 필수
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id, @RequestBody DeleteTodoRequestDto deleteTodoRequestDto) {
        todoService.deleteTodo(id, deleteTodoRequestDto);
    }
}
