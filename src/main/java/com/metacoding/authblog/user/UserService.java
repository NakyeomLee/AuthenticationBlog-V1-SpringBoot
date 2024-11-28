package com.metacoding.authblog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User userPS = userRepository.findByUsername(loginDTO.getUsername());

        if (!userPS.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
            
            // 실제로는 로그를 남겨서 서버에 패스워드가 실패 카운팅해야 함
            // 일단은 alert 보이도록 해놓음
        }
        return userPS;
    }
}