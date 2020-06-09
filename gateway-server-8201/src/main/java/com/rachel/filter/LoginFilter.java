package com.rachel.filter;

import com.rachel.common.service.UserService;
import com.rachel.common.service.UserTokenService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class LoginFilter implements GlobalFilter, Ordered {

    @Reference(check = false)
    private UserTokenService userTokenService;

    private static String[] excludePath = {"/api/code/create","/api/code/validate","/api/user/register","/api/user/login","/api/user/isRegistered"};

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取到Request中的token信息
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String requestPath = exchange.getRequest().getPath().pathWithinApplication().value();
        for (String path : excludePath) {
            if(requestPath.startsWith(path)){
                return chain.filter(exchange);
            }
        }
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        List<String> accessToken = queryParams.get("accessToken");
        String token = null;
        if(null != accessToken){
            token = accessToken.get(0);
        }
        if(null == token || null == userTokenService.getInfoByToken(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
            String data = "请求认证失败，请登录系统";
            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
            return response.writeWith(Mono.just(wrap));
        }
        return chain.filter(exchange);
    }
}
