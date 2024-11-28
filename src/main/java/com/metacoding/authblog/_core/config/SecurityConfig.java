package com.metacoding.authblog._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// IoC 컨테이너에 띄우기(어노테이션 달기)
@Configuration 
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // 시큐리티 필터 체인은 필터(걸러냄)
    // 매개변수로 HttpSecurity 의존성 주입
    @Bean // Bean은 IoC에서 싱글턴으로 관리됨
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // csrf 보호 기능 비활성화(disable) => 꼭 필요한 경우에만 하기
        http.csrf(c -> c.disable());

        // 해당 주소는 인증이 필요하고(requestMatchers(주소).authenticated()) => 이 경우 /s로 시작하는 모든 url
        // 이외에는 인증 필요없음 (.anyRequest().permitAll())
        // loginPage(주소) 로그인 페이지, loginProcessingUrl(주소) 로그인 시 걸리는 액션
        // defaultSuccessUrl(주소) 로그인 성공하면 갈 주소
        http.authorizeHttpRequests(r ->
                r.requestMatchers("/s/**").authenticated().anyRequest().permitAll())
                .formLogin(f ->
                        f.loginPage("/login-form")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/"));

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
        return http.build();
    }
}