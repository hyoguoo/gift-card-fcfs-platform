package com.hyoguoo.apigatewayservice.filter;

import com.hyoguoo.apigatewayservice.service.UserStatusService;
import com.hyoguoo.apigatewayservice.util.JwtProvider;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private static final String X_USER_ID_HEADER = "X-USER-ID";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final int SUBSTRING_LENGTH = TOKEN_PREFIX.length() + 1;
    private final JwtProvider jwtProvider;
    private final UserStatusService userStatusService;

    @Autowired
    public AuthorizationFilter(JwtProvider jwtProvider, UserStatusService userStatusService) {
        super(Config.class);
        this.jwtProvider = jwtProvider;
        this.userStatusService = userStatusService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (isWhitelistedUrl(config, exchange)) {
                return chain.filter(exchange);
            }
            ServerHttpRequest serverHttpRequest = exchange.getRequest();
            String token = this.resolveToken(serverHttpRequest);

            Long userIdFromAccessToken = jwtProvider.getUserIdFromAccessToken(token);

            return userStatusService.isUserActive(userIdFromAccessToken)
                    .flatMap(isActive -> {
                        if (isActive.equals(Boolean.FALSE)) {
                            return onError(exchange);
                        }

                        ServerHttpRequest modifiedRequest = addUserIdHeaderToRequest(serverHttpRequest,
                                userIdFromAccessToken);

                        return chain.filter(exchange.mutate().request(modifiedRequest).build());
                    });
        };
    }

    private boolean isWhitelistedUrl(Config config, ServerWebExchange exchange) {
        return config.urlWhitelist.contains(exchange.getRequest().getPath().value());
    }

    private ServerHttpRequest addUserIdHeaderToRequest(ServerHttpRequest serverHttpRequest,
            Long userIdFromAccessToken) {
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

    private Mono<Void> onError(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    @Data
    public static class Config {

        private List<String> urlWhitelist;
    }
}
