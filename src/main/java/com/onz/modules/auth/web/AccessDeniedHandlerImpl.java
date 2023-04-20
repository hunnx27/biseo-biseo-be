package com.onz.modules.auth.web;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final HandlerExceptionResolver handlerExceptionResolver;

    public AccessDeniedHandlerImpl(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        if (accessDeniedException != null) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if(authentication != null && !authentication.isAuthenticated()){
//                JsonObject jsonObject = new JsonObject();
//                jsonObject.addProperty("code", (String) request.getAttribute("exception"));
//                jsonObject.addProperty("message", accessDeniedException.getMessage());
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().print(jsonObject);
//            }
            handlerExceptionResolver.resolveException(request, response, null,
                accessDeniedException);
        }

    }
}
