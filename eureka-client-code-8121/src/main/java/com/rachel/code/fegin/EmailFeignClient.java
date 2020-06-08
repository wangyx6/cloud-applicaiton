package com.rachel.code.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "service-client-email")
@RequestMapping("/email")
public interface EmailFeignClient {

    @GetMapping(value = "/{email}/{code}")
    boolean sendEmailCode(@PathVariable(value = "email") String email, @PathVariable(value = "code") String code);
}
