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

        public User toEntity(PasswordEncoder passwordEncoder) {
            // PasswordEncoder : 패스워드 암호화
            String encPassword = passwordEncoder.encode(password);
            User user = new User(null, username, encPassword, email, null);
            return user;
        }
    }
}