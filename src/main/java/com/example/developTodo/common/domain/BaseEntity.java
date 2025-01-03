package com.example.developTodo.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 공통 엔티티 속성을 정의하는 추상 클래스
 * - 모든 엔티티에서 생성 시간과 수정 시간을 자동으로 관리
 * - JPA의 Auditing 기능을 사용하여 자동으로 필드를 업데이트
 * - @MappedSuperClass : 이 클래스를 상속받는 엔티티가 이 클래스의 필드를 컬럼으로 포함하도록 설정하는 애너테이션
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created_date;

    @LastModifiedDate
    private LocalDateTime modified_date;
}
