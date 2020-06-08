package com.rachel.email.controller;

import com.rachel.common.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/{email}/{code}")
    public boolean sendEmailCode(@PathVariable String email, @PathVariable String code){
        return emailService.sendAuthCodeEamil(email, code);
    }
}
