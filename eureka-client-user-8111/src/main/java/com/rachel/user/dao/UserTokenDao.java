package com.rachel.user.dao;

import com.rachel.common.po.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenDao extends JpaRepository<UserToken, Long> {
}
