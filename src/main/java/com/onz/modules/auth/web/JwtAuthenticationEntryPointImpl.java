package com.onz.modules.auth.web;

import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        log.error("Responding with unauthorized error. Message - {}", authException.getMessage());
//
//        String attribute = (String) request.getAttribute("unauthorization.code");
//
//        request.setAttribute("response.failure.code", attribute);
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, attribute);
        throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN_DETAIL, new String[]{authException.getMessage()});

    }
}
