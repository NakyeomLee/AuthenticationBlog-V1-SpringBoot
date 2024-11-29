package com.metacoding.authblog.user;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserRequest {

    // DTO는 불필요한 데이터 빼고 필요한 데이터만

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public class JoinDTO {
        private String username;
        private String password;
        private String email;

        // User를 초기화 할 때, 비밀번호는 기존 값을 hash화하여 초기화 한 뒤 반환
        public User toEntity(PasswordEncoder passwordEncoder) {
            // PasswordEncoder : 패스워드 해쉬화(암호화)
            String encPassword = passwordEncoder.encode(password);
            User user = new User(null, username, encPassword, email, null);
            System.out.println(encPassword);
            return user;
        }
    }
}