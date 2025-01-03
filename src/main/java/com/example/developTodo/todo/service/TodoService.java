package com.example.developTodo.todo.service;

import com.example.developTodo.common.utils.PasswordEncoder;
import com.example.developTodo.todo.domain.DeleteTodoRequestDto;
import com.example.developTodo.todo.domain.TodoResponseDto;
import com.example.developTodo.todo.domain.TodoWithNameResponseDto;
import com.example.developTodo.todo.domain.UpdateTodoRequestDto;
import com.example.developTodo.todo.domain.Todo;
import com.example.developTodo.user.domain.User;
import com.example.developTodo.common.exception.AuthorizeException;
import com.example.developTodo.todo.infrastructure.TodoRepository;
import com.example.developTodo.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final PasswordEncoder passwordEncoder;

    // 일정 생성
    @Transactional
    public TodoResponseDto save(String title, String contents, String username) {
        User findUser = userRepository.findUserByUsername(username).orElseThrow(() -> new IllegalArgumentException("일치하는 회원이 없습니다."));

        Todo todo = new Todo(title, contents);
        todo.setUser(findUser);

        todoRepository.save(todo);

        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getContents());
    }

    // 전체 일정 조회
    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll()
                .stream()
                .map(TodoResponseDto::toDto)
                .toList();
    }

    // 특정 일정 조회
    public TodoWithNameResponseDto findById(Long id) {
        Todo findTodo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("찾으려는 조건의 일정이 존재하지 않습니다."));

        User author = findTodo.getUser();

        return new TodoWithNameResponseDto(findTodo.getTitle(), findTodo.getContents(), author.getUsername());
    }

    // 일정 내용 수정
    public TodoResponseDto updateTodo(Long id, UpdateTodoRequestDto updateTodoRequestDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 조건의 일정이 존재하지 않습니다."));

        if (todo.getUser().matchesPassword(updateTodoRequestDto.getPw(), passwordEncoder)) {
            throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
        }

        todo.updateTodo(updateTodoRequestDto.getTitle(), updateTodoRequestDto.getContents());
        Todo saveTodo = todoRepository.save(todo);

        return new TodoResponseDto(saveTodo);
    }

    // 일정 삭제
    @Transactional
    public void deleteTodo(Long id, DeleteTodoRequestDto deleteTodoRequestDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일치하는 조건의 일정이 없습니다."));

        if (!passwordEncoder.matches(deleteTodoRequestDto.getPw(), todo.getUser().getPw())) {
            throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
        }
        todoRepository.delete(todo);
    }
}
