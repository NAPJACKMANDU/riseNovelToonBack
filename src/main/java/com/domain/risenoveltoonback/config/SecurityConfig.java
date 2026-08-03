package com.domain.risenoveltoonback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // REST API 개발 시 CSRF 비활성화
            .cors(cors -> {}) // WebConfig에 설정한 CORS 규칙 적용
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // 모든 요청에 대해 인증 없이 접근 허용!
            )
            .formLogin(login -> login.disable()) // 기본 로그인 폼 비활성화
            .httpBasic(basic -> basic.disable()); // 기본 HTTP Basic 인증 비활성화

        return http.build();
    }
}