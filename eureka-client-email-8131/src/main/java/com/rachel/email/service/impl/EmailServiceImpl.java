package com.rachel.email.service.impl;

import com.rachel.email.factory.MailFactory;
import com.rachel.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private MailFactory mailFactory;
    @Override
    public boolean sendAuthCodeEamil(String email, String content) {
        return mailFactory.sendEmail(email, content);
    }
}
