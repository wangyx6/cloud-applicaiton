package com.rachel.common.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "lagou_auth_code")
public class EmailCodePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String code;

    @Column(name = "createtime")
    private Date createTime;

    @Column(name = "expiretime")
    private Date expireTime;
}
