package com.hyoguoo.apigatewayservice.filter;

import com.hyoguoo.apigatewayservice.util.JwtProvider;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private static final String X_USER_ID_HEADER = "X-USER-ID";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final int SUBSTRING_LENGTH = TOKEN_PREFIX.length() + 1;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthorizationFilter(JwtProvider jwtProvider) {
        super(Config.class);
        this.jwtProvider = jwtProvider;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (isWhitelistedUrl(config, exchange)) {
                return chain.filter(exchange);
            }
            ServerHttpRequest modifiedRequest = addUserIdHeaderToRequest(exchange);
            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
    }

    private boolean isWhitelistedUrl(Config config, ServerWebExchange exchange) {
        return config.urlWhitelist.contains(exchange.getRequest().getPath().value());
    }

    private ServerHttpRequest addUserIdHeaderToRequest(ServerWebExchange exchange) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String token = this.resolveToken(serverHttpRequest);

        Long userIdFromAccessToken = jwtProvider.getUserIdFromAccessToken(token);

        return serverHttpRequest.mutate()
                .header(X_USER_ID_HEADER, String.valueOf(userIdFromAccessToken))
                .build();
    }

    private String resolveToken(ServerHttpRequest serverHttpRequest) {
        String bearerToken = serverHttpRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(SUBSTRING_LENGTH);
        }

        return null;
    }

    @Data
    public static class Config {

        private List<String> urlWhitelist;
    }
}
