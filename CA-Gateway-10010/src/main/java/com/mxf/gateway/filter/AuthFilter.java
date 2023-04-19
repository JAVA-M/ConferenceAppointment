package com.mxf.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.mxf.domain.Result;
import com.mxf.gateway.config.IgnoreWhiteProperties;
import com.mxf.utils.JWTUtils;
import com.mxf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author manxingfu
 * @date 2023/2/20
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // logger.info("白名单: {}", ignoreWhite.getWhites());
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites())) {
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("token");
        if (StringUtils.isEmpty(token)) {
            return unauthorizedResponse(exchange, "令牌不能为空");
        }
        Map<String, Claim> claims = null;
        try {
            claims = JWTUtils.verify(token).getClaims();
        } catch (Exception e) {
            e.printStackTrace();
            return unauthorizedResponse(exchange, "令牌解析错误！");
        }
        if (claims == null) {
            return unauthorizedResponse(exchange, "令牌已过期或验证不正确！");
        }

        // 内部请求来源参数清除
        removeHeader(mutate, "from-source");

        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name) {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        logger.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSON.toJSONString(Result.failure(msg)).getBytes());

        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
