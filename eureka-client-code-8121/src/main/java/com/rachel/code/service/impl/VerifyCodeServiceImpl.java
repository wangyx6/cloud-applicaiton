package com.rachel.code.service.impl;

import com.rachel.common.po.AuthCodePO;
import com.rachel.common.utils.RandomCodeUtil;
import com.rachel.code.dao.VerifyCodeDao;
import com.rachel.code.service.VerifyCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Resource
    private VerifyCodeDao verifyCodeDao;

    @Override
    public String createEmailCode(String email) {
        AuthCodePO authCodePO = new AuthCodePO();
        authCodePO.setEmail(email);
        // 生产验证码
        String verifyCode = RandomCodeUtil.getRandomCode(6);
        authCodePO.setCode(verifyCode);
        long currentTime = System.currentTimeMillis();
        long expiredTime = currentTime  + (10 * 60 * 1000);
        authCodePO.setCreateTime(new Date(currentTime));
        authCodePO.setExpireTime(new Date(expiredTime));
        verifyCodeDao.save(authCodePO);
        return verifyCode;
    }

    @Override
    public Integer authCodeByEmail(String email, String code) {
        AuthCodePO authCodePO = verifyCodeDao.findLastByEmailOrderByCreateTime(email);
        if(null == authCodePO.getId() || !authCodePO.getCode().equals(code)){
            return 1;
        }
        if(authCodePO.getExpireTime().before(new Date())){
            return 2;
        }
        return 0;
    }
}
