package com.metacoding.authblog._core;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class BCryptTest {

    @Test
    public void encode_test() {
        String password = "1234";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedPassword = encoder.encode(password);
        System.out.println(encodedPassword);
    }
}