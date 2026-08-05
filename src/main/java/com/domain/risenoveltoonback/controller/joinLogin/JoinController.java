package com.domain.risenoveltoonback.controller.joinLogin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.risenoveltoonback.model.joinlogin.JoinFormDto;
import com.domain.risenoveltoonback.model.joinlogin.DuplicateCheckDto;
import com.domain.risenoveltoonback.service.JoinService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor 
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<String> joinController(@RequestBody JoinFormDto signUpForm) {
        System.out.println(">>>>>>>> 회원가입 요청 들어옴: " + signUpForm.toString());
        
        // Service 호출
       return joinService.join(signUpForm);
    }

    @GetMapping("/duplicateCheck")
    public ResponseEntity<String> getDuplicateCheck(@ModelAttribute DuplicateCheckDto checkData) {
        System.out.print(">>>>>>>>>>>>>>>>>>>" + checkData.getTitle());
        return joinService.duplicateCheck(checkData);
    }
    
}
