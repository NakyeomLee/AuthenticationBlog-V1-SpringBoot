package com.metacoding.authblog._core.config;

import com.metacoding.authblog.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// IoC 컨테이너에 띄우기(어노테이션 달기)
@Configuration
public class SecurityConfig {

    // PasswordEncoder 빈 설정
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder를 사용하여 비밀번호를 안전한 해쉬 형식으로 변환(암호화)
        return new BCryptPasswordEncoder();
    }

    // SecurityFilterChain 빈 설정
    // 시큐리티 필터 체인은 필터(걸러냄)
    // 매개변수로 HttpSecurity 의존성 주입 => 보안 설정 구성
    @Bean // Bean은 IoC에서 싱글턴으로 관리됨
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // csrf 보호 기능 비활성화(disable) => 꼭 필요한 경우에만 하기(Spring은 기본적으로 활성화돼있음)
        // CSRF(Cross-Site Request Forgery) : 사용자의 권한을 악용해 악의적인 요청을 서버로 보내는 공격(사이트 간 요청 위조)
        http.csrf(c -> c.disable());

        // 요청 인가 설정(url에 따른 인증/권한 설정 정의)
        http.authorizeHttpRequests(r ->
                        // "/s/**"로 시작하는 URL 요청은 인증이 필요
                        // anyRequest().permitAll() : 나머지 모든 요청은 인증 없이 접근 허용
                        r.requestMatchers("/s/**").authenticated().anyRequest().permitAll())
                // 폼 로그인 설정(로그인 페이지와 로그인 처리 url 설정)
                .formLogin(f ->
                        f.loginPage("/login-form") // 로그인 폼 경로 설정
                                .loginProcessingUrl("/login") // 로그인 처리 경로 설정
//                                .defaultSuccessUrl("/")
                                .successHandler((request, response, authentication) -> {
                                    // authentication : 인증 정보 담김
                                    // getPrincipal()에 User 정보 담김
                                    // => 로그인(인증)된 User의 정보를 들고옴
                                    User user = (User) authentication.getPrincipal(); 
                                    HttpSession session = request.getSession();
                                    session.setAttribute("sessionUser", user);
                                    response.sendRedirect("/"); // 리다이렉트 직접시켜줌
                                }));

        // 권한 설정 필터링 예시 코드 (선생님이 슬랙으로 줌)
        // 프로젝트 참고용 - 관리자일때 특정 페이지 권한 허용, 회원가입 시 유저 권한 부여
        // 최초 관리자 권한은 DB에 강제 insert
//        http.authorizeHttpRequests(r ->
//                        r.requestMatchers("/s/**").hasAnyRole("USER", "ADMIN")
//                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                                .anyRequest().permitAll())
//                .formLogin(f ->
//                        f.loginPage("/login-form")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/"));
//
        // 보안 설정 기반으로 SecurityFilterChain 객체 반환
        return http.build();
    }
}