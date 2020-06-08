package com.rachel.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomCodeUtil {

    private final static Logger LOG = LoggerFactory.getLogger(RandomCodeUtil.class);

    /**
     * 生产规定位数的验证码
     * @param codeLength 验证码长度
     * @return 验证码
     */
    public static String getRandomCode(Integer codeLength) {
        if(codeLength <= 4){
            LOG.info("The minimum length of the verification code is 4 digits, and a random 4 digit verification code is returned");
            codeLength = 4;
        }
        Random r = new Random();
        String code = "";
        for (int i = 0; i < codeLength; ++i) {
            int temp = r.nextInt(52);
            char x = (char) (temp < 26 ? temp + 97 : (temp % 26) + 65);
            code += x;
        }
        return code;
    }
}
