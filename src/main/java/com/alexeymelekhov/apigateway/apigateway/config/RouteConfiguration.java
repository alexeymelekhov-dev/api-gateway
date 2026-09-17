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

    @Bean
    public RouterFunction<ServerResponse> flowManagerRoute() {
        return route("flow-manager")
                .route(
                        request -> request.path().startsWith("/api/v1/"),
                        http()
                )
                .before(UserLoginFilter.addUserLoginHeader("X-User-Login"))
                .filter(lb("flow-manager"))
                .build();
    }
}

