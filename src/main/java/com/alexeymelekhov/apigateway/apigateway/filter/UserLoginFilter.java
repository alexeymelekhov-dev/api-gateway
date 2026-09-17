package com.alexeymelekhov.apigateway.apigateway.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.function.Function;

public class UserLoginFilter  {

    public static Function<ServerRequest, ServerRequest> addUserLoginHeader(String headerName) {

        return request -> {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuthentication) {

                String login = jwtAuthentication
                        .getToken()
                        .getClaimAsString("preferred_username");

                return ServerRequest
                        .from(request)
                        .header(headerName, login)
                        .build();
            }

            return request;
        };
    }
}
