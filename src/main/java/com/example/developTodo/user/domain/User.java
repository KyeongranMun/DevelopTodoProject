package com.example.developTodo.user.domain;

import com.example.developTodo.common.utils.PasswordEncoder;
import com.example.developTodo.common.exception.AuthorizeException;
import com.example.developTodo.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String email;

    public User() {
    }

    public User(String username, String pw, String email, PasswordEncoder password) {
        this.username = username;
        this.email = email;
        this.pw = password.encode(pw);
    }

    public boolean matchesPassword(String pw, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(pw, this.pw)) {
            throw new AuthorizeException("비밀번호가 일치하지 않습니다.");
        }
        return false;
    }

    // 회원 정보 수정 - 사용자명, 이메일
    public void updateUserInfo(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
