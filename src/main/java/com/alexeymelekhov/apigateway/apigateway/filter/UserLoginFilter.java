package com.alexeymelekhov.apigateway.apigateway.filter;

import com.alexeymelekhov.apigateway.apigateway.exception.ErrorMessage;
import com.alexeymelekhov.apigateway.apigateway.exception.UserLoginNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.Optional;
import java.util.function.Function;

public class UserLoginFilter  {

    private static final String PREFERRED_USERNAME_CLAIM = "preferred_username";

    public static Function<ServerRequest, ServerRequest> addUserLoginHeader(String headerName) {

        return request -> {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof JwtAuthenticationToken jwtAuthentication) {

                Optional<String> login = Optional.ofNullable(
                        jwtAuthentication
                                .getToken()
                                .getClaimAsString(PREFERRED_USERNAME_CLAIM)
                );

                if (login.isEmpty()) {
                    throw new UserLoginNotFoundException(ErrorMessage.USER_LOGIN_MISSING.getMessage());
                }

                return ServerRequest
                        .from(request)
                        .header(headerName, String.valueOf(login))
                        .build();
            }

            return request;
        };
    }
}
