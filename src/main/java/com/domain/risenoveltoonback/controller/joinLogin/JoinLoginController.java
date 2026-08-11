package com.domain.risenoveltoonback.controller.joinLogin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinlogin.LoginFormDto;
import com.domain.risenoveltoonback.jwt.JwtToken;
import com.domain.risenoveltoonback.model.ApiResponse;
import com.domain.risenoveltoonback.model.joinlogin.DuplicateCheckDto;
import com.domain.risenoveltoonback.service.JoinLoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor 
public class JoinLoginController {

    private final JoinLoginService joinLoginService;

    @PostMapping("/join") // 회원가입
    public ResponseEntity<ApiResponse<Void>>  joinController(@RequestBody JoinFormDto signUpForm) {
       return joinLoginService.join(signUpForm);
    }

    @GetMapping("/duplicateCheck") // 아이디, 닉네임 중복 확인
    public ResponseEntity<ApiResponse<Void>> getDuplicateCheck(@ModelAttribute DuplicateCheckDto checkData) {
        return joinLoginService.duplicateCheck(checkData);
    }

    @PostMapping("/login") // 로그인
    public ResponseEntity<ApiResponse<JwtToken>> loginController(@RequestBody LoginFormDto loginForm) {
       return joinLoginService.login(loginForm);
    }

    @PostMapping("/reissue")    
    public ResponseEntity<ApiResponse<JwtToken>> reissue(@RequestBody JwtToken jwtToken) {        
        return joinLoginService.reissue(jwtToken.getRefreshToken());    
    }
}
