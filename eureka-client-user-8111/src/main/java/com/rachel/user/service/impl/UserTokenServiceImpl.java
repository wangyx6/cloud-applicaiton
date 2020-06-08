package com.rachel.user.service.impl;

import com.rachel.common.po.UserToken;
import com.rachel.user.dao.UserTokenDao;
import com.rachel.common.service.UserTokenService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private UserTokenDao userTokenDao;

    @Override
    public String getLoginToken(String email) {
        String token = UUID.randomUUID().toString();
        // 根据当前emial获取token信息

        UserToken userToken = new UserToken();
        userToken.setEmail(email);
        Example<UserToken> example = Example.of(userToken);
        Optional<UserToken> tokenOptional = userTokenDao.findOne(example);
        if(tokenOptional.isPresent()){
            userToken = tokenOptional.get();
        }
        userToken.setToken(token);
        userTokenDao.save(userToken);
        return token;
    }

    @Override
    public UserToken getInfoByToken(String token) {
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        Example<UserToken> example = Example.of(userToken);
        Optional<UserToken> tokenOptional = userTokenDao.findOne(example);
        if(tokenOptional.isPresent()){
            return tokenOptional.get();
        }
        return null;
    }
}
