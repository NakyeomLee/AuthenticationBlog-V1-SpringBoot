package com.metacoding.authblog.board;

import com.metacoding.authblog.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/board/save-form")
    public String saveForm() {

        // 이렇게 작성하지않으면? => 로그인(인증)되지않은 상태에서 글쓰기가 가능할거고
        // 글 실컷 다 적고 글쓰기 버튼 눌렀는데 로그인하라고 로그인 페이지로 넘어가버릴거고
        // 로그인 하고 왔더니 다 적어놓은거 날아가서 다시 적어야될거고 짜증나고
        // 그러니까 로그인(인증) 안했으면 글쓰기 페이지 진입 못하도록 막아놓은것
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new RuntimeException("인증되지 않음 401");
        }

        return "board/save-form";
    }
}