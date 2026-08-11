package com.domain.risenoveltoonback.config;

import com.domain.risenoveltoonback.jwt.JwtTokenProvider;
import com.domain.risenoveltoonback.jwt.JwtAuthenticationFilter;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   private final JwtTokenProvider jwtTokenProvider;

    SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable())
        // ▼▼▼ 1. H2 Console 화면 깨짐 방지 (Frame 허용) ▼▼▼
        .headers((headers) -> headers                
        .frameOptions((frame) -> frame.sameOrigin())            
    ) 
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/api/login", "/api/join", "/api/duplicateCheck").permitAll()
            // H2 Console 접속 허용                
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .anyRequest().authenticated()
        )
        // [PART 5] JWT 필터 등록            
        // 기존의 UsernamePasswordAuthenticationFilter 앞에 우리가 만든 필터를 끼워 넣음            
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), 
        UsernamePasswordAuthenticationFilter.class);

    return http.build();
}

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    
    // 비밀번호 암호화 빈 등록
    @Bean    
    public PasswordEncoder passwordEncoder() {        
        return new BCryptPasswordEncoder();    
    }
}