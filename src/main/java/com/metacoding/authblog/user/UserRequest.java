package com.metacoding.authblog.user;

import lombok.Data;

public class UserRequest {

    // DTO는 불필요한 데이터 빼고 필요한 데이터만

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}