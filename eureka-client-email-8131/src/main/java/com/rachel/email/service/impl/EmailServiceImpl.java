package com.rachel.email.service.impl;

import com.rachel.common.service.EmailService;
import com.rachel.email.factory.MailFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private MailFactory mailFactory;
    @Override
    public boolean sendAuthCodeEamil(String email, String content) {
        return mailFactory.sendEmail(email, content);
    }
}
