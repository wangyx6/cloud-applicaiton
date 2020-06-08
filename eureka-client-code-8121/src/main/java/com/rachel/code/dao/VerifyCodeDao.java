package com.rachel.code.dao;

import com.rachel.common.po.AuthCodePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VerifyCodeDao extends JpaRepository<AuthCodePO, Long> {

    @Query(value = "select * from lagou_auth_code where email=?1 order by createtime desc limit 1", nativeQuery = true)
    AuthCodePO findLastByEmailOrderByCreateTime(String email);
}
