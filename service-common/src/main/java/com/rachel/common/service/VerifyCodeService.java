package com.rachel.common.service;

public interface VerifyCodeService {

    /**
     * 根据email生产，发送注册验证码
     * @param email
     * @return
     */
    String createEmailCode(String email);

    /**
     * 根据email信息，获取验证码信息
     * @param email
     * @return
     */
    Integer authCodeByEmail(String email, String code);
}
