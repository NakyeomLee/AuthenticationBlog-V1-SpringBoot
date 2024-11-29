package com.metacoding.authblog._core.filter;

import com.metacoding.authblog.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    // FilterChain : 다음 필터 호출하기 위한 매개변수
    // doFilter 메서드 오버라이드 : 요청을 가로채고 처리 수행 후 다음 필터 또는 서블릿으로 전달
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("돌고 있나?");

        // 다운캐스팅
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 리퀘스트 객체에서 세션 가져오기
        HttpSession session = req.getSession();

        // 세션에서 로그인된 사용자 정보를 담은 sessionUser를 가져옴
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 인증 여부 검사
        if (sessionUser == null) {
            resp.sendRedirect("/login-form"); // 로그인 페이지로 리다이렉트

        } else {
            // 인증된 사용자 처리
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}