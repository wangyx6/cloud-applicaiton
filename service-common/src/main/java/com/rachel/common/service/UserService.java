package com.rachel.common.service;

import com.rachel.common.po.UserPO;

public interface UserService {

    boolean saveUser(UserPO userPO);

    UserPO userLogin(UserPO userPO);

    UserPO getUserByEmail(String email);
}
