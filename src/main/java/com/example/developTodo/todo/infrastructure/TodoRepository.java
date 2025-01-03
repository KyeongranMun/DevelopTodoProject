package com.example.developTodo.todo.infrastructure;

import com.example.developTodo.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
