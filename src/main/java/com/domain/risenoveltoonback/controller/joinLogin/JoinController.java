package com.domain.risenoveltoonback.controller.joinLogin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.service.JoinService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor // 👈 joinService 자동 주입 (생성자 주입)
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<String> webToonMypage(@RequestBody JoinFormDto signUpForm) {
        System.out.println(">>>>>>>> 회원가입 요청 들어옴: " + signUpForm.toString());
        
        // Service 호출
       return joinService.join(signUpForm);
    }
}
