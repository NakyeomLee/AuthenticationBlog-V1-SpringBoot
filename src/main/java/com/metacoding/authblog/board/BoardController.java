package com.metacoding.authblog.board;

import com.metacoding.authblog.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    // @AuthenticationPrincipal : 현재 인증된 사용자의 정보를 쉽게 가져올 수 있도록 해줌
    @GetMapping("/s/board/save-form") // /s/**경로에 인증된 User만 접근 가능 
    public String saveForm(@AuthenticationPrincipal User user) {

        // @AuthenticationPrincipal 해놔서 로그인하는 사용자 정보 가져올 수 있음
        System.out.println("로그인 : " + user.getUsername());

        return "board/save-form";
    }
}