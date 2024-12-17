package com.example.developTodo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity { // abstract class(추상클래스)로 생성해주면 다른 곳에서 인스턴스화 시켜 사용하지 못함

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_date;

    @LastModifiedDate
    private LocalDateTime modified_date;
}
