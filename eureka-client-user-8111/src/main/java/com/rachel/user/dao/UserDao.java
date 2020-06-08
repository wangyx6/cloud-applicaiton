package com.rachel.user.dao;

import com.rachel.common.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserPO, Long> {

}
