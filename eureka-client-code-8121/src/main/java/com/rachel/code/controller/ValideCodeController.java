package com.rachel.code.controller;

import com.rachel.common.service.EmailService;
import com.rachel.common.service.VerifyCodeService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class ValideCodeController {

    @Reference(check = false)
    private VerifyCodeService verifyCodeService;

    @Reference
    private EmailService emailService;

    @GetMapping("/create/{email}")
    public Boolean getVerifyCode(@PathVariable String email){
        String code = verifyCodeService.createEmailCode(email);
        return emailService.sendAuthCodeEamil(email, code);
    }

    @GetMapping("/validate/{email}/{code}")
    public Integer validateCode(@PathVariable String email, @PathVariable String code){
        return verifyCodeService.authCodeByEmail(email, code);
    }
}
