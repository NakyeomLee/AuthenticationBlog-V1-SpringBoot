package com.metacoding.authblog.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor // 풀 생성자
@NoArgsConstructor // 디폴트(빈) 생성자
@Table(name = "user_tb") // 테이블명
@Getter
@Entity // 엔티티, 이거 달아놔야 테이블 생성 가능
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private Integer id; // Null일것도 감안해서 Integer

    @Column(unique = true, nullable = false) // 컬럼제약조건 unique(index 알아서 만들어질것), not null
    private String username; // 유저 아이디는 username으로 약속되어있음, id 아님
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    private Timestamp createdAt;
}