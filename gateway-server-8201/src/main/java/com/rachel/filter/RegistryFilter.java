package com.rachel.filter;

import com.rachel.model.RegistryPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class RegistryFilter implements GlobalFilter,Ordered {

    private Map<String, RegistryPO> registyCountMap = new HashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // ip爆刷应该是使用redis,这个可以定时清理掉那些正常注册的ip地址，只把重新注册的ip地址过滤掉
        // 此处暂时使用map去实现
        ServerHttpResponse response = exchange.getResponse();

        // 判断是否是注册接口，其他接口一律方向
        String requestPath = exchange.getRequest().getPath().pathWithinApplication().value();
        if(!requestPath.startsWith("/api/code/create")){
            return chain.filter(exchange);
        }
        System.out.println("请求路径=====" + requestPath);
        String remoteIp = exchange.getRequest().getRemoteAddress().getHostString();
        RegistryPO registryPO = registyCountMap.get(remoteIp);
        if(null == registryPO){
            registryPO = new RegistryPO();
            registryPO.setIp(remoteIp);
            registryPO.setCount(1);
            registryPO.setTimeStamp(System.currentTimeMillis());
            registyCountMap.put(remoteIp, registryPO);
        } else {
            // 判断当前ip是否在1分钟内注册次数超过10次
            if(registryPO.getTimeStamp() + 1000*60 > System.currentTimeMillis() && registryPO.getCount() > 10){
                // 决绝访问，返回
                response.setStatusCode(HttpStatus.UNAUTHORIZED); // 状态码
                log.debug("=====>IP:" + remoteIp + " 存在批量重复注册，将提示操作过于频繁！ ");
                String data = "很抱歉,您操作过于频繁";
                DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());
                return response.writeWith(Mono.just(wrap));
            } else if(registryPO.getTimeStamp() + 1000*60 < System.currentTimeMillis()){
                registryPO.setTimeStamp(System.currentTimeMillis());
                registryPO.setCount(1);
            } else {
                registryPO.setCount(registryPO.getCount() + 1);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
