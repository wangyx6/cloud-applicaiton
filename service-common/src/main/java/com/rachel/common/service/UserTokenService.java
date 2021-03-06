package com.rachel.common.service;

import com.rachel.common.po.UserToken;

public interface UserTokenService {

    String getLoginToken(String email);

    UserToken getInfoByToken(String token);
}
