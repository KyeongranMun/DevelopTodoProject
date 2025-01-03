package com.example.developTodo.user.infrastructure;

import com.example.developTodo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // username으로 유저를 찾는 메서드
    Optional<User> findUserByUsername(String username);
    // email로 유저를 찾는 메서드
    Optional<User> findUsersByEmail(String Email);
}
