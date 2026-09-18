package com.alexeymelekhov.apigateway.apigateway.config;

import com.alexeymelekhov.apigateway.apigateway.filter.UserLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
class RouteConfiguration {

    private static final String HEADER_USER_LOGIN = "X-User-Login";
    public static final String FLOW_MANAGER = "flow-manager";

    @Bean
    public RouterFunction<ServerResponse> flowManagerRoute() {
        return route(FLOW_MANAGER)
                .route(
                        request -> request.path().startsWith("/api/v1/"),
                        http()
                )
                .before(UserLoginFilter.addUserLoginHeader(HEADER_USER_LOGIN))
                .filter(lb(FLOW_MANAGER))
                .build();
    }
}

