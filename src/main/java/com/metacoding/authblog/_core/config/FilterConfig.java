package com.metacoding.authblog._core.config;

import com.metacoding.authblog._core.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration // @Component 포함 / 설정 파일에는 @Configuration
public class FilterConfig {

    @Bean // 리턴값이 IOC에 등록됨, @Bean이 붙어있는 메소드 찾아내서 필터 장착시킴
    public FilterRegistrationBean addAuthFilter() {
        FilterRegistrationBean rg = new FilterRegistrationBean();
        rg.setFilter(new AuthenticationFilter());

        // 주소가 /board 이거나 /user로 시작하는 경우에만 (*은 모든)
        rg.addUrlPatterns("/board/*");
        rg.addUrlPatterns("/user/*");
        rg.setOrder(1); // 필터 순서 설정

        return rg;
    }
}