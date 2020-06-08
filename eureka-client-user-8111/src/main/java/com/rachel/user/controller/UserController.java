package com.rachel.user.controller;

import com.rachel.common.po.UserPO;
import com.rachel.common.po.UserToken;
import com.rachel.common.service.UserService;
import com.rachel.common.service.UserTokenService;
import com.rachel.common.service.VerifyCodeService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(check = false)
    private VerifyCodeService verifyCodeService;
    @Reference
    private UserService userService;
    @Reference
    private UserTokenService userTokenService;

    @GetMapping("/register/{email}/{password}/{code}")
    public Boolean registUser(@PathVariable String email, @PathVariable String password, @PathVariable String code){
        // 验证验证码是否正确
        Integer result = verifyCodeService.authCodeByEmail(email, code);
        if(result != 0){
            return false;
        }
        UserPO userPO = new UserPO();
        userPO.setEmail(email);
        userPO.setPassword(password);
        // 保存用户信息
        return userService.saveUser(userPO);
    }

    @GetMapping("/isRegistered/{email}")
    public Boolean isRegistered(@PathVariable String email){
        UserPO userPO = userService.getUserByEmail(email);
        if(null != userPO){
            return true;
        }
        return false;
    }

    @GetMapping("/login/{email}/{password}")
    public String login(@PathVariable String email, @PathVariable String password){
        UserPO userPO = new UserPO();
        userPO.setEmail(email);
        userPO.setPassword(password);
        UserPO user = userService.userLogin(userPO);
        if(null == user){
            return "用户名或密码错误";
        }
        return userTokenService.getLoginToken(email);
    }


    @GetMapping("/info/{token}")
    public String getTokenEmail(@PathVariable String token){
        UserToken userToken = userTokenService.getInfoByToken(token);
        return null == userToken ? null : userToken.getEmail();
    }

    @GetMapping("/test")
    public String test(){
        return "123";
    }
}
