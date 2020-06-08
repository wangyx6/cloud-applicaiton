package com.rachel.user.service.impl;

import com.rachel.common.po.UserPO;
import com.rachel.common.service.UserService;
import com.rachel.user.dao.UserDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean saveUser(UserPO userPO) {
        // 判断用户邮箱是否存在
        UserPO queryObject = new UserPO();
        queryObject.setEmail(userPO.getEmail());
        Example<UserPO> example = Example.of(queryObject);
        Optional<UserPO> userPOOptional = userDao.findOne(example);
        if(!userPOOptional.isEmpty()){
            return false;
        }
        userDao.save(userPO);
        return true;
    }

    @Override
    public UserPO userLogin(UserPO userPO) {
        // 判断用户邮箱是否存在
        UserPO queryObject = new UserPO();
        queryObject.setEmail(userPO.getEmail());
        Example<UserPO> example = Example.of(queryObject);
        Optional<UserPO> userPOptional = userDao.findOne(example);
        if(userPOptional.isEmpty() || !userPOptional.get().getPassword().equals(userPO.getPassword())){
            return null;
        }
        return userPOptional.get();
    }

    @Override
    public UserPO getUserByEmail(String email) {
        // 判断用户邮箱是否存在
        UserPO queryObject = new UserPO();
        queryObject.setEmail(email);
        Example<UserPO> example = Example.of(queryObject);
        if(!userDao.findOne(example).isPresent()){
            return null;
        }
        return userDao.findOne(example).get();
    }
}
