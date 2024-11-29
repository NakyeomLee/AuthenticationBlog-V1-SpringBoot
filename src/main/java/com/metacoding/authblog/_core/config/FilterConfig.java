package com.metacoding.authblog._core.config;

import com.metacoding.authblog._core.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정 파일에는 @Configuration, 그 외 용도의 파일에는 @Component

//@Configuration // @Component 포함
public class FilterConfig {

    @Bean // 리턴값이 IOC에 등록됨, @Bean이 붙어있는 메소드 찾아내서 필터 장착시킴
    public FilterRegistrationBean addAuthFilter() {
        // FilterRegistrationBean 객체를 생성하고, AuthenticationFilter를 필터로 설정
        // AuthenticationFilter는 사용자 인증을 처리하는 필터
        FilterRegistrationBean rg = new FilterRegistrationBean();
        rg.setFilter(new AuthenticationFilter());

        // URL 패턴 등록
        // 주소가 /board 이거나 /user로 시작하는 경우에만 (*은 모든)
        rg.addUrlPatterns("/board/*");
        rg.addUrlPatterns("/user/*");

        rg.setOrder(1); // 필터 실행 순서 설정

        // addAuthFilter() 메서드는 FilterRegistrationBean을 반환하고,
        // 반환값은 IoC 컨테이너에 등록됨
        return rg;
    }
}