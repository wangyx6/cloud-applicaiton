package com.rachel.user.fegin;

import com.rachel.common.resources.ServerContent;
import com.rachel.common.response.ResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// name：调用的服务名称，和服务提供者yml文件中spring.application.name保持一致
@FeignClient(name = ServerContent.SERVER_CODE)
@RequestMapping("/code")
public interface CodeFeignClient {

    @GetMapping("/create/{email}")
    Boolean getVerifyCode(@PathVariable(value = "email") String email);


    @GetMapping("/validate/{email}/{code}")
    Integer validateCode(@PathVariable(value = "email") String email, @PathVariable(value = "code") String code);
}
