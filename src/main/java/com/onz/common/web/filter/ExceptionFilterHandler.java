package com.onz.common.web.filter;

import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.common.exception.CustomException;
import com.onz.common.web.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class ExceptionFilterHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch(CustomException e){
            log.error("Spring Security Filter Chain Custom Exception:", e);
            setErrorResponse(e.getErrorCode(), response, e.getArgs());
        } catch(Exception e) {
            log.error("Spring Security Filter Chain Exception:", e);

            setErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR_DETAIL, response, new String[]{e.getMessage()});
        }

    }

    public void setErrorResponse(ErrorCode errorCode, HttpServletResponse response, String... args) {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        ErrorResponse errorReponse = new ErrorResponse(errorCode, args);
        try {
            String json = errorReponse.convertToJSON();
            log.error(json);
            byte[] body = json.getBytes(StandardCharsets.UTF_8);
            response.getOutputStream().write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
