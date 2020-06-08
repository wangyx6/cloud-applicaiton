package com.rachel.common.po;

import lombok.Data;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Data
@Entity
@Table(name = "lagou_token")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String token;
}
