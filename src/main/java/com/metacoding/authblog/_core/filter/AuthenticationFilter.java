package com.metacoding.authblog._core.filter;

import com.metacoding.authblog.user.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    // FilterChain : 다음 필터 호출하기 위한 매개변수
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("돌고 있나?");

        // 다운캐스팅
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // 리퀘스트 객체에서 세션 가져오기
        HttpSession session = req.getSession();

            User sessionUser = (User) session.getAttribute("sessionUser");

            if (sessionUser == null) {
                resp.sendRedirect("/login-form");

            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
    }
}