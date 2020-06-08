package com.rachel.code.controller;

import com.rachel.code.fegin.EmailFeignClient;
import com.rachel.code.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class ValideCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private EmailFeignClient emailFeignClient;

    @GetMapping("/create/{email}")
    public Boolean getVerifyCode(@PathVariable String email){
        String code = verifyCodeService.createEmailCode(email);
        return emailFeignClient.sendEmailCode(email, code);
    }

    @GetMapping("/validate/{email}/{code}")
    public Integer validateCode(@PathVariable String email, @PathVariable String code){
        return verifyCodeService.authCodeByEmail(email, code);
    }
}
