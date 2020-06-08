package com.rachel.common.service;

public interface EmailService {

    /**
     * 根据email生产，发送注册验证码
     * @param email
     * @return
     */
    boolean sendAuthCodeEamil(String email, String content);
}
